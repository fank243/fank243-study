package com.fank243.study.common.core.web.handler;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.common.core.exception.FeignException;
import com.fank243.study.common.core.utils.ResultInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常
 *
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@Slf4j
@Order(-2)
@RestControllerAdvice
@ConditionalOnWebApplication(type = SERVLET)
public class StudyExceptionHandler {

    /** OpenFeign调用异常 */
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

}
