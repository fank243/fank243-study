package com.fank243.study.feign.exception;

import com.fank243.study.core.exception.GlobalExceptionHandler;
import com.fank243.study.core.utils.ResultInfo;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，优先级要高于 {@link GlobalExceptionHandler}
 *
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Slf4j
@Order(-1)
@RestControllerAdvice
public class FeignExceptionHandler {

    /** FeignException.ServiceUnavailable */
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResultInfo<?> validServiceUnavailable(FeignException.ServiceUnavailable e) {
        log.error("OpenFeign异常拦截[FeignException.ServiceUnavailable]：{}，{}", e.getMessage(), e);
        return ResultInfo.error("系统内部错误[服务暂不可用]，请稍后重试", e.toString());
    }

    /** FeignException.NotFound */
    @ExceptionHandler(FeignException.NotFound.class)
    public ResultInfo<?> validNotFound(FeignException.NotFound e) {
        log.error("OpenFeign异常拦截[FeignException.NotFound]：{}，{}", e.getMessage(), e);
        return ResultInfo.error("系统内部错误[请求地址不存在]，请稍后重试", e.toString());
    }

    /** FeignException.MethodNotAllowed */
    @ExceptionHandler(FeignException.MethodNotAllowed.class)
    public ResultInfo<?> validMethodNotAllowed(FeignException.MethodNotAllowed e) {
        log.error("OpenFeign异常拦截[FeignException.MethodNotAllowed]：{}，{}", e.getMessage(), e);
        return ResultInfo.error("系统内部错误[请求方式不被支持]", e.toString());
    }

    /** FeignException.GatewayTimeout */
    @ExceptionHandler(FeignException.GatewayTimeout.class)
    public ResultInfo<?> validGatewayTimeout(FeignException.GatewayTimeout e) {
        log.error("OpenFeign异常拦截[FeignException.GatewayTimeout]：{}，{}", e.getMessage(), e);
        return ResultInfo.error("系统内部错误[请求服务超时]，请稍后重试", e.toString());
    }

    /** FeignException.TooManyRequests */
    @ExceptionHandler(FeignException.TooManyRequests.class)
    public ResultInfo<?> validTooManyRequests(FeignException.TooManyRequests e) {
        log.error("OpenFeign异常拦截[FeignException.TooManyRequests]：{}，{}", e.getMessage(), e);
        return ResultInfo.error("系统内部错误[请求过于频繁]，请稍后重试", e.toString());
    }

    /** FeignException */
    @ExceptionHandler(FeignException.class)
    public ResultInfo<?> validFeignException(FeignException e) {
        log.error("OpenFeign异常拦截[FeignException]：{}，{}", e.getMessage(), e);
        return ResultInfo.error("系统内部错误[" + e.getLocalizedMessage() + "]，请稍后重试", e.toString());
    }

}
