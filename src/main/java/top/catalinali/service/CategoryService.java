package top.catalinali.service;

import top.catalinali.dataobject.ProductCategory;

import java.util.List;

/**
 * <pre>
 * Description: 类目
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/19
 * </pre>
 */
public interface CategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

    ProductCategory save(ProductCategory category);
}
