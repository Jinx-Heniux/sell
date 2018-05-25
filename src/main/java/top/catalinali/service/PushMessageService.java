package top.catalinali.service;

import top.catalinali.dto.OrderDto;

/**
 * @Author: lx
 * @Description: 推送消息
 * @Date: Created on 14:36 2018/5/25
 * @Modefied by:
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDto
     */
    void orderStatus(OrderDto orderDto);
}
