package top.catalinali.service;

import top.catalinali.dataobject.SellerInfo;

/**
 * 卖家端
 * Created by lllx on 2018/5/13.
 */
public interface SellerService {


    /**
     * 根据openid查找卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellInfoByOpenid(String openid);
}
