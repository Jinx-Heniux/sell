package top.catalinali.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.catalinali.dto.OrderDto;
import top.catalinali.enums.OrderStatusEnum;
import top.catalinali.service.PushMessageService;

import java.math.BigDecimal;

/**
 * @Author: lx
 * @Description: 模板推送
 * @Date: Created on 16:48 2018/5/25
 * @Modefied by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    private static final String openid ="oY6GT0XsmEjiJST4AijvEyW0IPIk";

    @Autowired
    private PushMessageService pushMessage;

    @Test
    public void orderStatus() {
        OrderDto dto = new OrderDto();
        dto.setBuyerOpenid(openid);
        dto.setOrderAmount(new BigDecimal(55));
        dto.setOrderStatus(OrderStatusEnum.NEW.getCode());
        dto.setOrderId("111");
        pushMessage.orderStatus(dto);
    }
}