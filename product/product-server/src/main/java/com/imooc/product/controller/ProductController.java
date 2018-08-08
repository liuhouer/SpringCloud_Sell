package com.imooc.product.controller;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.dto.CartDTO;
import com.imooc.product.service.CategoryService;
import com.imooc.product.service.ProductService;
import com.imooc.product.util.ConverterUtils;
import com.imooc.product.util.ResultVOUtils;
import com.imooc.product.vo.ProductInfoVO;
import com.imooc.product.vo.ProductVO;
import com.imooc.product.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hellozjf
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 查询类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
//    @CrossOrigin(allowCredentials = "true")     // allowCredentials=true，允许cookie跨域
    public ResultVO<ProductVO> list(HttpServletRequest request) {

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                log.debug("{}={}", cookie.getName(), cookie.getValue());
            }
        }

        // 1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2. 查询类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        // 3. 查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = ConverterUtils.productCategory2ProductVO(productCategory);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    productInfoVOList.add(ConverterUtils.productInfo2ProductInfoVO(productInfo));
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }

    /**
     * 获取商品列表（给订单服务用的）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {

        log.debug("listForOrder productIdList={}", productIdList);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<ProductInfo> productInfoList = productService.findList(productIdList);
        log.debug("productIdList={}", productIdList);
        log.debug("productInfoList={}", productInfoList);
        return ConverterUtils.productInfoList2ProductInfoOutputList(productInfoList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        log.debug("decreaseStockInputList = {}", decreaseStockInputList);
        List<CartDTO> cartDTOList = ConverterUtils.decreaseStockInputList2CartDTOList(decreaseStockInputList);
        productService.decreaseStock(cartDTOList);
    }
}
