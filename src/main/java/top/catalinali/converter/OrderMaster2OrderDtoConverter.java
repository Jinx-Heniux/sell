package top.catalinali.converter;

import org.springframework.beans.BeanUtils;
import top.catalinali.dataobject.OrderMaster;
import top.catalinali.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/30
 * </pre>
 */
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster master){
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(master,dto);
        return dto;
    }

    public static List<OrderDto> convert(List<OrderMaster> masters){
        return masters.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
