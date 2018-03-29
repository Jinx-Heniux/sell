package top.catalinali.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.catalinali.dataobject.ProductInfo;
import top.catalinali.dto.CartDto;
import top.catalinali.enums.ProductStatusEnum;
import top.catalinali.enums.ResultEnum;
import top.catalinali.exception.SellException;
import top.catalinali.repository.ProductInfoRepository;
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
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo info) {
        return repository.save(info);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDTOList) {
        for (CartDto cartDto : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int stock = productInfo.getProductStock() + cartDto.getProductQuentity();
            productInfo.setProductStock(stock);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDTOList) {
        for (CartDto cartDto : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() - cartDto.getProductQuentity();
            if(stock < 0 ){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stock);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = this.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (ProductStatusEnum.UP.equals(productInfo.getProductStatusEnum())) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
