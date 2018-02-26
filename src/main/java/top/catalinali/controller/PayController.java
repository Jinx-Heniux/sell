package top.catalinali.controller;

import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.catalinali.dto.OrderDto;
import top.catalinali.enums.ResultEnum;
import top.catalinali.exception.SellException;
import top.catalinali.service.OrderService;
import top.catalinali.service.PayService;

import java.util.Map;

/**
 * <pre>
 * Description: 支付
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/2/5
 * </pre>
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl")String returnUrl,
                               Map<String,Object> map){
        //查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse response = payService.create(orderDto);
        map.put("payResponse", response);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("/pay/create",map);
    }

    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        //返回微信处理结果
        return new ModelAndView("pay/success");
    }
}
