package top.catalinali.service;

import top.catalinali.dto.OrderDto;

/**
 * <pre>
 * Description: 买家
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/31
 * </pre>
 */
public interface BuyerService {

    public OrderDto findOrderOne(String openid,String orderId);

    public OrderDto cancelOrder(String openid,String orderId);
}
