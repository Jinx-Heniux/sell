package top.catalinali.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.catalinali.dataobject.ProductCategory;
import top.catalinali.dataobject.ProductInfo;
import top.catalinali.service.CategoryService;
import top.catalinali.service.ProductService;
import top.catalinali.util.ResultVOUtil;
import top.catalinali.vo.BaseResponse;
import top.catalinali.vo.ProductInfoVO;
import top.catalinali.vo.ProductVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Description: 买家商品
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/22
 * </pre>
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品列表
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<ProductVO>> list(){
        //查询所有上架商品
        List<ProductInfo> upAll = productService.findUpAll();
        //查询所有上架商品的类目
        //传统方法
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : upAll) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //lambda表达式
        List<Integer> categoryTypeList = upAll.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : upAll) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
