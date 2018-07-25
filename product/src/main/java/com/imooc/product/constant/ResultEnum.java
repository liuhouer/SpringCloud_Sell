package com.imooc.product.constant;

import lombok.Getter;

/**
 * @author hellozjf
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
