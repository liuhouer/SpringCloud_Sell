package com.imooc.product.util;

import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.vo.ProductInfoVO;
import com.imooc.product.vo.ProductVO;
import org.springframework.beans.BeanUtils;

/**
 * @author hellozjf
 */
public class TypeUtils {

    public static ProductVO productCategory2ProductVO(ProductCategory productCategory) {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(productCategory, productVO);
        return productVO;
    }

    public static ProductInfoVO productInfo2ProductInfoVO(ProductInfo productInfo) {
        ProductInfoVO productInfoVO = new ProductInfoVO();
        BeanUtils.copyProperties(productInfo, productInfoVO);
        return productInfoVO;
    }
}
