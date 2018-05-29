package top.catalinali.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: lx
 * @Description:
 * @Date: Created on 15:49 2018/5/29
 * @Modefied by:
 */
@Data
public class ProductForm {


    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;
}
