package com.imooc.order.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author hellozjf
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体的内容
     */
    private T data;
}
