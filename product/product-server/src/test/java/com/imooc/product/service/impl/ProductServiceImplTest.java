package com.imooc.product.service.impl;

import com.imooc.product.ProductApplicationTests;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hellozjf
 */
@Slf4j
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        log.debug("productInfoList = {}", productInfoList);
        Assert.assertTrue(productInfoList.size() > 0);
    }

    @Test
    public void findList() {
        List<String> productIdList = Arrays.asList("157875196366160022", "157875227953464068");
        List<ProductInfo> productInfoList = productService.findList(productIdList);
        log.debug("productInfoList = {}", productInfoList);
        Assert.assertTrue(productInfoList.size() > 0);
    }

    @Test
    public void decreaseStock() {
        List<CartDTO> cartDTOList = new ArrayList<>();
        CartDTO cartDTO = new CartDTO("157875196366160022", 2);
        cartDTOList.add(cartDTO);
        productService.decreaseStock(cartDTOList);
    }
}