package com.fank243.study.core.web.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends Exception implements Serializable {

    private Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();

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
