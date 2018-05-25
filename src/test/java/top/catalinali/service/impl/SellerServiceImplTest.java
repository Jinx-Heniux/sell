package top.catalinali.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.catalinali.dataobject.SellerInfo;
import top.catalinali.service.SellerService;

/**
 * Created by lllx on 2018/5/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

    private static final String openid = "abc";

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellInfoByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerService.findSellInfoByOpenid(openid);
        Assert.assertNotNull(openid,sellerInfo.getOpenid());
    }

}