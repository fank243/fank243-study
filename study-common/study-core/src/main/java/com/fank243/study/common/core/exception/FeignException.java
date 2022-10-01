package com.fank243.study.common.core.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FeignException
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeignException extends RuntimeException implements Serializable {

    private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    private final String message;

    public FeignException(String message) {
        this.message = message;
    }

    public FeignException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
