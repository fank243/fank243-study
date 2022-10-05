package com.fank243.study.common.core.exception;

/**
 * 异常基类
 * 
 * @author FanWeiJie
 * @since 2022-10-03 22:23:55
 */
public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
