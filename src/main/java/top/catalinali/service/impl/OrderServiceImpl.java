package top.catalinali.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.catalinali.converter.OrderMaster2OrderDtoConverter;
import top.catalinali.dataobject.OrderDetail;
import top.catalinali.dataobject.OrderMaster;
import top.catalinali.dataobject.ProductInfo;
import top.catalinali.dto.CartDto;
import top.catalinali.dto.OrderDto;
import top.catalinali.enums.OrderStatusEnum;
import top.catalinali.enums.PayStatusEnum;
import top.catalinali.enums.ResultEnum;
import top.catalinali.exception.SellException;
import top.catalinali.repository.OrderDetailRepository;
import top.catalinali.repository.OrderMasterRepository;
import top.catalinali.service.OrderService;
import top.catalinali.service.PayService;
import top.catalinali.service.ProductService;
import top.catalinali.service.WebSocket;
import top.catalinali.util.KeyUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/27
 * </pre>
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PayService payService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //orderDetail订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3. 写入订单数据库orderMaster
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4. 扣库存
        List<CartDto> cartDtos = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDtos);

        //发送websocket消息
        webSocket.sendMessage(orderDto.getOrderId());

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> details = orderDetailRepository.findByOrderId(orderId);
        if(details.isEmpty()){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, dto);
        dto.setOrderDetailList(details);
        return dto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> masters = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> dtos = OrderMaster2OrderDtoConverter.convert(masters.getContent());
        return new PageImpl<OrderDto>(dtos, pageable, masters.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster master = new OrderMaster();
        //判断订单状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.error("【取消订单】订单状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,master);
        OrderMaster orderMaster = orderMasterRepository.save(master);
        if(orderMaster == null){
            log.error("【取消订单】取消订单失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单】订单中午商品详情，orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtos = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDtos);
        //若已支付，则退款
        if(PayStatusEnum.SUCCESS.equals(orderDto.getPayStatus())){
            payService.refund(orderDto);
        }
        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.error("【完结订单】订单状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster master = new OrderMaster();
        BeanUtils.copyProperties(orderDto,master);
        OrderMaster orderMaster = orderMasterRepository.save(master);
        if(orderMaster == null){
            log.error("【完结订单】完结订单失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.error("【订单支付完成】订单状态不正确，orderId={}, orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!PayStatusEnum.WAIT.getCode().equals(orderDto.getOrderStatus())){
            log.info("【订单支付完成】订单支付状态不正确，orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findAll(pageable);
        List<OrderDto> dtoList = OrderMaster2OrderDtoConverter.convert(orderMasters.getContent());
        return new PageImpl<OrderDto>(dtoList,pageable,orderMasters.getTotalElements());
    }
}
