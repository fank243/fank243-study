package com.github.fank243.study.oauth2.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.fank243.common.result.ResultInfo;

import cn.dev33.satoken.oauth2.exception.SaOAuth2Exception;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据源异常拦截
 *
 * @author FanWeiJie
 * @since 2021-06-16 22:20:34
 */
@Slf4j
@Order(-1)
@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ExceptionHandler(SaOAuth2Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultInfo<?> handlerSaOAuth2Exception(SaOAuth2Exception e) {
        log.error("Oauth2认证异常：{}", e.getMessage(), e);
        return ResultInfo.err401("Oauth2认证异常").error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String accept = request.getHeader("accept");
        log.error("统一认证异常，MediaType：{}，异常信息：{}", accept, e.getMessage(), e);
        if (StrUtil.contains(accept, "text/html")) {
            response.sendRedirect("/error/500?message=" + URLEncodeUtil.encode(e.getMessage()));
        }
        throw e;
    }
}
