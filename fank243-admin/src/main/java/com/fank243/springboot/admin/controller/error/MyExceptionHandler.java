package com.fank243.springboot.admin.controller.error;

import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultCode;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 异常拦截
 * 
 * @author FanWeiJie
 * @date 2020-03-28 22:32:04
 */
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    /** 业务异常，控制事务回滚 **/
    @ExceptionHandler(BizException.class)
    public Object handleBizException(HttpServletRequest request, HttpServletResponse response, BizException e) {
        if (log.isWarnEnabled()) {
            log.warn(e.getResult().toString());
        }
        // ajax
        if (WebUtils.isAjax(request)) {
            WebUtils.printJson(response, e.getResult());
            return null;
        }
        // 页面
        ModelAndView view = new ModelAndView("error/500");
        view.addObject("message", e.getResult().getMsg());
        return view;
    }

    /** Ajax 参数验证 **/
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultInfo handleValidationException(BindException e) {
        if (log.isInfoEnabled()) {
            log.info(e.getBindingResult().getTarget() + "：" + e.toString());
        }
        return ResultInfo.illegalParameter(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }

    /** 401 **/
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleUnauthorizedException(HttpServletRequest request, HttpServletResponse response,
        UnauthorizedException e) {
        if (log.isInfoEnabled()) {
            log.info(e.toString());
        }
        // ajax
        if (WebUtils.isAjax(request)) {
            WebUtils.printJson(response, ResultInfo.unauthorized("您访问的资源需要授权哦【" + e.getMessage() + "】"));
            return null;
        }
        // 页面
        ModelAndView view = new ModelAndView("error/401");
        view.addObject("message", "您访问的资源需要授权哦【" + e.getMessage() + "】");
        return view;
    }

    /** 404 */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object noHandlerFoundException(HttpServletRequest request, HttpServletResponse response,
        NoHandlerFoundException e) {
        if (log.isInfoEnabled()) {
            log.warn(e.toString());
        }
        // ajax
        if (WebUtils.isAjax(request)) {
            WebUtils.printJson(response, ResultInfo.notFund());
            return null;
        }
        // 页面
        ModelAndView view = new ModelAndView("error/404");
        view.addObject("message", ResultCode.R404.getMsg());
        return view;
    }

    /** 405 **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Object handleMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response,
        HttpRequestMethodNotSupportedException e) {
        if (log.isInfoEnabled()) {
            log.warn(e.toString());
        }
        // ajax
        if (WebUtils.isAjax(request)) {
            WebUtils.printJson(response, ResultInfo.methodNotSupported());
            return null;
        }
        // 页面
        ModelAndView view = new ModelAndView("error/msg");
        view.addObject("message", ResultCode.R405.getMsg());
        return view;
    }

    /** 全局异常 **/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Object handledException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (log.isWarnEnabled()) {
            log.warn(e.toString(), e);
        }
        // ajax
        if (WebUtils.isAjax(request)) {
            WebUtils.printJson(response, ResultInfo.exception("系统出错了【" + e.getMessage() + "】"));
            return null;
        }
        // 页面
        ModelAndView view = new ModelAndView("error/500");
        view.addObject("message", "系统出错了【" + e.getMessage() + "】");
        return view;
    }
}
