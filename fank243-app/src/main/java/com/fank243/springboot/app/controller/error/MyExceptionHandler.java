package com.fank243.springboot.app.controller.error;

import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * 异常拦截
 * 
 * @author FanWeiJie
 * @date 2020-03-28 22:32:04
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    /** 业务异常，控制事务回滚 **/
    @ExceptionHandler(BizException.class)
    public ResultInfo handleBizException(BizException e) {
        if (log.isWarnEnabled()) {
            log.warn(e.getResult().toString());
        }
        return e.getResult();
    }

    /** 参数验证 **/
    @ExceptionHandler(BindException.class)
    public ResultInfo handleValidationException(BindException e) {
        if (log.isInfoEnabled()) {
            log.warn(e.getBindingResult().getTarget() + "：" + e.toString());
        }
        return ResultInfo.illegalParameter(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }

    /** 401 */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultInfo noHandlerFoundException(UnauthorizedException e) {
        if (log.isInfoEnabled()) {
            log.warn(e.toString());
        }
        return ResultInfo.unauthorized();
    }

    /** 404 */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultInfo noHandlerFoundException(NoHandlerFoundException e) {
        if (log.isInfoEnabled()) {
            log.warn(e.toString());
        }
        return ResultInfo.notFund();
    }

    /** 405 **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultInfo handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        if (log.isInfoEnabled()) {
            log.warn(e.toString());
        }
        return ResultInfo.methodNotSupported();
    }

    /** 全局异常 **/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResultInfo handledException(Exception e) {
        if (log.isWarnEnabled()) {
            log.warn(e.toString(), e);
        }
        return ResultInfo.exception();
    }
}
