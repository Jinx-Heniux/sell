package top.catalinali.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import top.catalinali.dataobject.ProductInfo;
import top.catalinali.enums.ProductStatusEnum;
import top.catalinali.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/21
 * </pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo one = productService.findOne("123456");
        Assert.assertEquals("123456",one.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest page = new PageRequest(0,10);
        Page<ProductInfo> all = productService.findAll(page);
        Assert.assertNotEquals(0,all.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

}