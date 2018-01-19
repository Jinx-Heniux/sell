package top.catalinali.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.catalinali.dataobject.ProductCategory;

/**
 * <pre>
 * Description: ProductCategoryRepositoryTest
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/15
 * </pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory category = repository.findOne(2);
        System.out.println(category);
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory category = new ProductCategory("男生最不爱",6);
        ProductCategory save = repository.save(category);
        Assert.assertNotNull(save);
    }
}