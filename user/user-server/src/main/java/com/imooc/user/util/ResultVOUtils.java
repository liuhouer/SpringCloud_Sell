package com.imooc.user.util;

import com.imooc.user.constant.ResultEnum;
import com.imooc.user.vo.ResultVO;

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

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());
        return resultVO;
    }
}
