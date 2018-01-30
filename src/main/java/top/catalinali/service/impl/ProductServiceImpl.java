package top.catalinali.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.catalinali.dataobject.ProductInfo;
import top.catalinali.dto.CartDto;
import top.catalinali.enums.ProductStatusEnum;
import top.catalinali.enums.ResultEnum;
import top.catalinali.exception.SellException;
import top.catalinali.repository.ProductInfoReportary;
import top.catalinali.service.ProductService;

import java.util.List;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/21
 * </pre>
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoReportary reportary;

    @Override
    public ProductInfo findOne(String productId) {
        return reportary.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return reportary.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return reportary.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo info) {
        return reportary.save(info);
    }

    @Override
    public void increaseStock(List<CartDto> cartDTOList) {

    }

    @Override
    public void decreaseStock(List<CartDto> cartDTOList) {
        for (CartDto cartDto : cartDTOList) {
            ProductInfo productInfo = reportary.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            Integer stock = productInfo.getProductStock() - cartDto.getProductQuentity();
            productInfo.setProductStock(stock);
            reportary.save(productInfo);
        }
    }
}
