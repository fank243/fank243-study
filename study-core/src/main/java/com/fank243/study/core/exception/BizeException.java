package com.fank243.study.core.exception;

import lombok.Data;

/**
 * 自定义异常
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Data
public class BizeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer status;

    private final String message;

    public BizeException(String message) {
        this.message = message;
    }

    public BizeException(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public BizeException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
