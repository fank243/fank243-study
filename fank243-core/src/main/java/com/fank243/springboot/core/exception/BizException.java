package com.fank243.springboot.core.exception;

import com.fank243.springboot.common.utils.ResultInfo;
import lombok.Getter;

/**
 * 业务异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
@Getter
public class BizException extends Exception {

    private ResultInfo result;

    public BizException(ResultInfo result) {
        this.result = result;
    }
}
