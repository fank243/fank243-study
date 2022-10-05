package com.fank243.study.common.core.exception;

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
public class BizException extends BaseException implements Serializable {

    private int status = HttpStatus.BAD_REQUEST.value();
    private final String message;

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
