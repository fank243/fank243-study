package com.fank243.study.core.exception;

import com.fank243.study.core.enums.ResultCodeEnum;
import com.fank243.study.core.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultInfo<?> validMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("异常拦截[MethodArgumentNotValidException]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R405, e.getMessage());
    }

    /**
     * 415 Content-type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResultInfo<?> validHttpMediaTypeNotSupportedException(MethodArgumentNotValidException e) {
        log.error("异常拦截[HttpMediaTypeNotSupportedException]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "不支持的Media Type", e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo<?> validHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("异常拦截[HttpMessageNotReadableException]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.BAD_REQUEST.value(), "非法请求，请求参数不是一个有效的JSON串", e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo<?> handleException(Exception e) {
        log.error("异常拦截[Exception]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R500.getMessage(), e.getMessage());
    }

}
