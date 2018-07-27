package com.imooc.product.common;

import lombok.Data;

/**
 * @author hellozjf
 */
@Data
public class DecreaseStockInput {

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
