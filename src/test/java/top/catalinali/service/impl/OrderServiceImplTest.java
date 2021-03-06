package top.catalinali.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import top.catalinali.dataobject.OrderDetail;
import top.catalinali.dto.OrderDto;
import top.catalinali.enums.OrderStatusEnum;
import top.catalinali.enums.PayStatusEnum;
import top.catalinali.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/27
 * </pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPENID = "1101110";

    private final String ORDER_ID = "1517036525936758212";

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("廖师兄");
        orderDto.setBuyerAddress("幕课网");
        orderDto.setBuyerPhone("123456789012");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1234568");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        log.info("【创建订单】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDto one = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",one);
        Assert.assertEquals(ORDER_ID,one.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        Pageable request = new PageRequest(0,2);
        Page<OrderDto> list = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0,list.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto one = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.cancel(one);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDTO = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDTO = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void findList1() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDTOPage = orderService.findList(request);
//        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表", orderDTOPage.getTotalElements() > 0);
    }

}