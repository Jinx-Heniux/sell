package top.catalinali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.catalinali.dataobject.SellerInfo;

/**
 * Created by lllx on 2018/5/13.
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{
    SellerInfo findByOpenid(String openid);
}
