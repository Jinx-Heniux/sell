package top.catalinali.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.catalinali.dataobject.ProductInfo;
import top.catalinali.dto.CartDto;

import java.util.List;

/**
 * <pre>
 * Description: 商品
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/21
 * </pre>
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo info);

    //加库存
    void increaseStock(List<CartDto> cartDTOList);

    //减库存
    void decreaseStock(List<CartDto> cartDTOList);

}
