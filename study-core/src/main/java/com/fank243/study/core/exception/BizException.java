package com.fank243.study.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends Exception {
    private static final long serialVersionUID = 1L;

    private Integer status = 500;

    private final String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public BizException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
