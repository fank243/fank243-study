package com.fank243.study.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * 认证异常
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

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
