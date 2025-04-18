/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.core.model.error.handler;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.exception.BizException;
import com.github.fank243.kong.core.properties.KongProperties;
import com.github.fank243.kong.core.utils.WebUtils;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
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

    /** Spring Validate 参数验证异常 */
    @ExceptionHandler(BindException.class)
    public ResultInfo<?> handlerBindException(BindException e) {
        log.error("全局异常拦截[BindException]：{}", e.getMessage(), e);
        return ResultInfo.err400(e.getAllErrors().get(0).getDefaultMessage());
    }

    /** 接口请求方法不支持异常 */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultInfo<?> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("全局异常拦截[405]：{}", e.getMessage(), e);
        return WebUtils.renderHtml(KongProperties.baseUrl, ResultInfo.err405().error(e.getMessage()));
    }

    /** 接口请求类型不支持异常 */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultInfo<?> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("全局异常拦截[HttpMediaTypeNotSupportedException]：{}", e.getMessage(), e);
        return WebUtils.renderHtml(KongProperties.baseUrl,
            ResultInfo.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getLocalizedMessage()).error(e.getMessage()));
    }

    /** 错误的请求异常 **/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultInfo<?> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("全局异常拦截[HttpMessageNotReadableException]：{}", e.getMessage(), e);
        return ResultInfo.error(HttpStatus.BAD_REQUEST.value(), "非法请求，请求参数不是一个有效的JSON串").error(e.getMessage());
    }

    /** SaToken认证鉴权异常 **/
    @ExceptionHandler(SaTokenException.class)
    public ResultInfo<?> handlerSaTokenException(SaTokenException e) {
        log.error("全局异常拦截[SaTokenException]：{}", e.getMessage(), e);
        if (e instanceof NotLoginException) {
            return ResultInfo.err401().error(e.getMessage());
        }
        return ResultInfo.err403().error(e.getMessage());
    }

    /** 业务异常 */
    @ExceptionHandler(BizException.class)
    public ResultInfo<?> handlerBizException(BizException e) {
        log.error("全局异常拦截[BizException]:{}", e.getLocalizedMessage(), e);
        return WebUtils.renderHtml(KongProperties.baseUrl,
            ResultInfo.error(e.getStatus(), e.getLocalizedMessage()).error(e.getMessage()));
    }

    /** 默认异常 */
    @ExceptionHandler(Exception.class)
    public ResultInfo<?> handleException(Exception e) {
        log.error("全局异常拦截[Exception]：{}", e.getMessage(), e);
        return WebUtils.renderHtml(KongProperties.baseUrl,
            ResultInfo.err500(e.getLocalizedMessage()).error(e.getMessage()));
    }

}
