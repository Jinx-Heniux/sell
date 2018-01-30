package top.catalinali.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.catalinali.dto.OrderDto;

/**
 * <pre>
 * Description: 订单service
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/27
 * </pre>
 */
public interface OrderService {

    OrderDto create(OrderDto orderDto);//创建订单

    OrderDto findOne(String orderId);//查询单个订单

    Page<OrderDto> findList(String buyerOpenId, Pageable pageable);//查询订单列表

    OrderDto cancel(OrderDto orderDto);//取消订单

    /** 完结订单. */
    OrderDto finish(OrderDto orderDTO);

    /** 支付订单. */
    OrderDto paid(OrderDto orderDTO);

    /** 查询订单列表. */
    Page<OrderDto> findList(Pageable pageable);
}
