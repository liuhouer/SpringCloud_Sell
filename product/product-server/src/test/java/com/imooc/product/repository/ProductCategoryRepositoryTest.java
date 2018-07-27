package com.imooc.product.repository;

import com.imooc.product.ProductApplicationTests;
import com.imooc.product.dataobject.ProductCategory;
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
public class ProductCategoryRepositoryTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 22));
        log.debug("productCategoryList = {}", productCategoryList);
        Assert.assertTrue(productCategoryList.size() > 0);
    }
}