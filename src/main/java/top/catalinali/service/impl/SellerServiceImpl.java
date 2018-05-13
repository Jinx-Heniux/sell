package top.catalinali.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.catalinali.dataobject.SellerInfo;
import top.catalinali.repository.SellerInfoRepository;
import top.catalinali.service.SellerService;

/**
 * Created by lllx on 2018/5/13.
 */
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellInfoByOpenid(String openid) {
        return  repository.findByOpenid(openid);
    }
}
