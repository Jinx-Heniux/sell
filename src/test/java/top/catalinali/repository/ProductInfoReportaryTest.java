package top.catalinali.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.catalinali.dataobject.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/19
 * </pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoReportaryTest {

    @Autowired
    private ProductInfoReportary reportary;

    @Test
    public void saveTest(){
        ProductInfo info = new ProductInfo();
        info.setProductId("123456");
        info.setProductName("皮蛋粥");
        info.setProductPrice(new BigDecimal(3.2));
        info.setProductStock(100);
        info.setProductDescription("很好喝的粥");
        info.setProductIcon("http://xxxxx.jpg");
        info.setProductStatus(0);
        info.setCategoryType(2);
        reportary.save(info);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> infos = reportary.findByProductStatus(0);
        Assert.assertNotEquals(0,infos.size());
    }

}