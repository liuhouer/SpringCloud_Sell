package com.imooc.order.util;

import com.imooc.order.constant.ResultEnum;
import com.imooc.order.vo.ResultVO;

/**
 * @author hellozjf
 */
public class ResultVOUtils {
    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.SUCCESS.getMessage());
        resultVO.setData(data);
        return resultVO;
    }
}
