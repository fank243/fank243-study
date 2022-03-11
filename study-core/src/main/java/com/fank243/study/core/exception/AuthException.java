package com.fank243.study.core.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 认证异常
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthException extends Exception implements Serializable {

    private int status = HttpStatus.UNAUTHORIZED.value();

    private final String message;

    public AuthException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}