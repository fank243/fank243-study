package com.fank243.study.common.core.web.handler;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fank243.study.common.core.constants.enums.ResultCodeEnum;
import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.common.core.exception.FeignException;
import com.fank243.study.common.core.utils.ResultInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 *
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@Slf4j
@Order(0)
@RestControllerAdvice
@ConditionalOnWebApplication(type = SERVLET)
public class GlobalExceptionHandler {

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResultInfo<?> handlerBindException(BindException e) {
        log.error("全局异常拦截[BindException]：{}", e.getMessage(), e);
        return ResultInfo.fail(e.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 405
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultInfo<?> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("全局异常拦截[405]：{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R405, e.getMessage());
    }

    /**
     * 415 Content-type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultInfo<?> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("全局异常拦截[HttpMediaTypeNotSupportedException]：{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getLocalizedMessage(), e.getMessage());
    }

    /**
     * 400
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultInfo<?> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("全局异常拦截[HttpMessageNotReadableException]：{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.BAD_REQUEST.value(), "非法请求，请求参数不是一个有效的JSON串", e.getMessage());
    }

    /**
     * 401
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResultInfo<?> handlerAuthException(AuthException e) {
        log.error("全局异常拦截[AuthException]：{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.toString());
    }

    /**
     * Feign 异常
     */
    @ExceptionHandler(FeignException.class)
    public ResultInfo<?> handleFeignException(FeignException e) {
        log.error("全局异常拦截[FeignException]：{}", e.getMessage(), e);
        return ResultInfo.error(e.getStatus(), e.getLocalizedMessage(), e.getMessage());
    }

    /** 业务异常 */
    @ExceptionHandler(BizException.class)
    public ResultInfo<?> handlerBizException(BizException e) {
        log.error("全局异常拦截[BizException]:{}", e.getLocalizedMessage(), e);
        return ResultInfo.error(e.getStatus(), e.getLocalizedMessage(), e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo<?> handleException(Exception e) {
        log.error("全局异常拦截[Exception]：{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R500.getStatus(), e.getLocalizedMessage(), e.getMessage());
    }

}
