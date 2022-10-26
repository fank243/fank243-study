package com.github.fank243.study.core.exception;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 算术运算
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArithmeticException extends Exception implements Serializable {

    private final String message;

    public ArithmeticException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
