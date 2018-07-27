package com.imooc.product.constant;

import lombok.Getter;

/**
 * @author hellozjf
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
