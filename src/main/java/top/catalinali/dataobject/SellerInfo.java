package top.catalinali.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lllx on 2018/5/13.
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;
    private String username;
    private String password;
    private String openId;
}
