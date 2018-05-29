package top.catalinali.dataobject;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/24
 * </pre>
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单id. */
    private String orderId;

    /** 商品id. */
    private String productId;

    /** 商品名称. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;

    public static void main(String[] args) {
        OrderDetail dto = new OrderDetail();
        dto.setProductPrice(new BigDecimal(0.5));
        dto.setProductQuantity(5);
        dto.setDetailId("1524822582798604722");
        dto.setOrderId("123321");
        dto.setProductId("1234568");
        dto.setProductIcon("www.baiduha");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(dto);
        System.out.println(new Gson().toJson(orderDetailList));
    }
}
