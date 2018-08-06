package com.imooc.user.constant;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hellozjf
 */
@Getter
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;
    private Integer code;
    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
