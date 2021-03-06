package top.catalinali.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import top.catalinali.dto.OrderDto;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/2/5
 * </pre>
 */
public interface PayService {

    PayResponse create(OrderDto orderDto);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDto orderDto);
}
