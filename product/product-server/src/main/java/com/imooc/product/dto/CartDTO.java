package com.imooc.product.dto;

import lombok.Data;

/**
 * @author hellozjf
 */
@Data
public class CartDTO {

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
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
