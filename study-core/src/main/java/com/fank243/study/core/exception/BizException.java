package com.fank243.study.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * 自定义异常
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

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
