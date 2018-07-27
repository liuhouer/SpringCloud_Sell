package com.imooc.product.repository;

import com.imooc.product.ProductApplicationTests;
import com.imooc.product.constant.ProductStatusEnum;
import com.imooc.product.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author hellozjf
 */
@Slf4j
public class ProductInfoRepositoryTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        log.debug("productInfoList = {}", productInfoList);
        Assert.assertTrue(productInfoList.size() > 0);
    }

    @Test
    public void findByProductIdIn() {
        List<String> productIdList = Arrays.asList("157875196366160022", "157875227953464068");
        List<ProductInfo> productInfoList = productInfoRepository.findByProductIdIn(productIdList);
        log.debug("productInfoList = {}", productInfoList);
        Assert.assertTrue(productInfoList.size() > 0);
    }
}