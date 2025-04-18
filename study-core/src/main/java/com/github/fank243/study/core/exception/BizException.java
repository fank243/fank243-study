package com.github.fank243.study.core.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 自定义异常
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class BizException extends BaseException implements Serializable {

    private final String message;
    private int status = HttpStatus.BAD_REQUEST.value();

    public BizException(String message) {
        this.message = message;
    }

    public BizException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
