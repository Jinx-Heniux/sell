package top.catalinali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.catalinali.dataobject.OrderDetail;

import java.util.List;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/24
 * </pre>
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{
    List<OrderDetail> findByOrderId(String orderId);
}
