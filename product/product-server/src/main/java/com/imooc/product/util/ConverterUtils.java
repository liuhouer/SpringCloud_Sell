package com.imooc.product.util;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.dto.CartDTO;
import com.imooc.product.vo.ProductInfoVO;
import com.imooc.product.vo.ProductVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hellozjf
 */
public class ConverterUtils {

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

    public static ProductInfoOutput productInfo2ProductInfoOutput(ProductInfo productInfo) {
        ProductInfoOutput productInfoOutput = new ProductInfoOutput();
        BeanUtils.copyProperties(productInfo, productInfoOutput);
        return productInfoOutput;
    }

    public static List<ProductInfoOutput> productInfoList2ProductInfoOutputList(List<ProductInfo> productInfoList) {
        return productInfoList.stream()
                .map(e -> productInfo2ProductInfoOutput(e))
                .collect(Collectors.toList());
    }

    public static CartDTO decreaseStockInput2CartDTO(DecreaseStockInput decreaseStockInput) {
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(decreaseStockInput, cartDTO);
        return cartDTO;
    }

    public static List<CartDTO> decreaseStockInputList2CartDTOList(List<DecreaseStockInput> decreaseStockInputList) {
        return decreaseStockInputList.stream()
                .map(e -> decreaseStockInput2CartDTO(e))
                .collect(Collectors.toList());
    }
}
