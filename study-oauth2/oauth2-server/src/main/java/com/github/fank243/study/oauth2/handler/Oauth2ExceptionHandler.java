package com.github.fank243.study.oauth2.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.HttpConstants;
import com.github.fank243.study.core.utils.WebUtils;

import cn.dev33.satoken.oauth2.exception.SaOAuth2Exception;
import cn.hutool.core.net.URLEncodeUtil;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResultInfo<?> handlerSaOauth2Exception(SaOAuth2Exception e) {
        log.error("Oauth2认证异常：{}", e.getMessage(), e);
        return ResultInfo.err401("Oauth2认证异常[" + e.getMessage() + "]").error(e.toString());
    }

    /**
     * 统一拦截 Spring Validator 针对请求参数的合法验证处理逻辑
     *
     * @param e Exception
     * @return ResponseContent
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultInfo<?> handlerValidException(Exception e) {
        String errMsg;
        if (e instanceof MethodArgumentNotValidException ex) {
            errMsg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } else {
            BindException ex = (BindException)e;
            errMsg = ex.getAllErrors().get(0).getDefaultMessage();
        }
        return ResultInfo.err400(errMsg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultInfo<?> handlerException(Exception e, HttpServletResponse response) throws Exception {
        log.error("统一认证异常，异常信息：{}", e.getMessage(), e);
        if (WebUtils.acceptTextHtml()) {
            response.sendRedirect(HttpConstants.ERROR_500 + "?message=" + URLEncodeUtil.encode(e.getMessage()));
        }
        return ResultInfo.err500(e.getMessage()).error(e.toString());
    }
}
