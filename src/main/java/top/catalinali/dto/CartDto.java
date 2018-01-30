package top.catalinali.dto;

import lombok.Data;

/**
 * <pre>
 * Description: 购物车DTO
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/27
 * </pre>
 */
@Data
public class CartDto {

    private String productId;

    private Integer productQuentity;

    public CartDto() {
    }

    public CartDto(String productId, Integer productQuentity) {
        this.productId = productId;
        this.productQuentity = productQuentity;
    }
}
