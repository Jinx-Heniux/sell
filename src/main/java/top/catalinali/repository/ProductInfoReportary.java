package top.catalinali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.catalinali.dataobject.ProductInfo;

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
public interface ProductInfoReportary extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer id);

}
