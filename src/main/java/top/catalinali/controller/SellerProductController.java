package top.catalinali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.catalinali.dataobject.ProductInfo;
import top.catalinali.exception.SellException;
import top.catalinali.service.ProductService;

/**
 * <pre>
 * Description: 卖家端商品
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/3/29
 * </pre>
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    /**
     * 卖家商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("list")
    public String list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                       @RequestParam(value = "size",defaultValue = "10")Integer size, ModelMap map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return "product/list";
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("on_sale")
    public String onSale(@RequestParam("productId") String productId, ModelMap map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return "common/error";
        }

        map.put("url", "/sell/seller/product/list");
        return "common/success";
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public String offSale(@RequestParam("productId") String productId,ModelMap map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return "common/error";
        }

        map.put("url", "/sell/seller/product/list");
        return "common/success";
    }



}
