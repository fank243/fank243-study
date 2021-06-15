package com.fank243.study.feign.exception;

import com.fank243.study.core.enums.ResultCodeEnum;
import com.fank243.study.core.exception.GlobalExceptionHandler;
import com.fank243.study.core.utils.ResultInfo;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResultInfo<?> validServiceUnavailable(FeignException.ServiceUnavailable e) {
        log.error("OpenFeign调用异常[FeignException.ServiceUnavailable]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R503.getStatus(), "服务调用异常[服务暂不可用]，请稍后重试", e.getLocalizedMessage());
    }

    /** FeignException.NotFound */
    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultInfo<?> validNotFound(FeignException.NotFound e) {
        log.error("OpenFeign调用异常[FeignException.NotFound]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R404.getStatus(), "服务调用异常[请求地址不存在]，请稍后重试", e.getLocalizedMessage());
    }

    /** FeignException.MethodNotAllowed */
    @ExceptionHandler(FeignException.MethodNotAllowed.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultInfo<?> validMethodNotAllowed(FeignException.MethodNotAllowed e) {
        log.error("OpenFeign调用异常[FeignException.MethodNotAllowed]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R503.getStatus(), "服务调用异常[请求方式不被支持]", e.getLocalizedMessage());
    }

    /** FeignException.GatewayTimeout */
    @ExceptionHandler(FeignException.GatewayTimeout.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ResultInfo<?> validGatewayTimeout(FeignException.GatewayTimeout e) {
        log.error("OpenFeign调用异常[FeignException.GatewayTimeout]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.GATEWAY_TIMEOUT.value(), "服务调用异常[请求服务超时]，请稍后重试", e.getLocalizedMessage());
    }

    /** FeignException.TooManyRequests */
    @ExceptionHandler(FeignException.TooManyRequests.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResultInfo<?> validTooManyRequests(FeignException.TooManyRequests e) {
        log.error("OpenFeign调用异常[FeignException.TooManyRequests]：{}，{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.TOO_MANY_REQUESTS.value(), "服务调用异常[请求过于频繁]，请稍后重试", e.getLocalizedMessage());
    }

    /** FeignException */
    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo<?> validFeignException(FeignException e) {
        log.error("OpenFeign调用异常[FeignException]：{}，{}", e.getMessage(), e);
        return ResultInfo.error("服务调用异常[" + e.getLocalizedMessage() + "]，请稍后重试", e.toString());
    }

}
