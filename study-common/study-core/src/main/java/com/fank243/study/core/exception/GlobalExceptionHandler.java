package com.fank243.study.core.exception;

import com.fank243.study.core.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Slf4j
@Order(0)
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public ResultInfo<?> baseException(BaseException e) {
        return ResultInfo.fail(e.getDefaultMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public ResultInfo<?> businessException(CustomException e) {
        if (e.getStatus() == null) {
            return ResultInfo.fail(e.getMessage());
        }
        return ResultInfo.fail(e.getStatus(), e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResultInfo<?> validatedBindException(BindException e) {
        log.error("异常拦截[BindException]：{}，{}", e.getMessage(), e);
        return ResultInfo.fail(e.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 405
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultInfo<?> validExceptionHandler(MethodArgumentNotValidException e) {
        log.error("异常拦截[MethodArgumentNotValidException]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo<?> handleException(Exception e) {
        log.error("异常拦截[Exception]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(e.getMessage());
    }

}
