package com.imooc.product.service.impl;

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
public class CategoryServiceImplTest extends ProductServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
        log.debug("productCategoryList = {}", productCategoryList);
        Assert.assertTrue(productCategoryList.size() > 0);
    }
}