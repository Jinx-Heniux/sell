package top.catalinali.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.catalinali.dataobject.ProductCategory;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
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

    @Test
    public void findByCatgoryTypeIn() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> catgorys = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,catgorys.size());
    }

    @Test
    public void updateTest(){
        ProductCategory one = repository.findOne(1);
        one.setCategoryName("大花花");
        ProductCategory save = repository.save(one);
        Assert.assertNotEquals(one,save);

    }
}