package com.github.fank243.study.core.model.log;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.github.fank243.study.core.utils.WebUtils;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 请求日志拦截打印
 * 
 * @author FanWeiJie
 * @date 2023-05-31 09:58
 */
@Component
@Aspect
public class LogAspect {

    @Around("execution(public * com.github.fank243.study.*.controller..*(..))")
    public Object round(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HttpServletRequest request = WebUtils.getRequest();
        if (request == null) {
            return point.proceed();
        }
        ReqRespLog reqRespLog = new ReqRespLog();
		if (StpUtil.isLogin()) {
			reqRespLog.setUserId(StpUtil.getLoginIdAsString());
		}
        reqRespLog.setReqUri(request.getRequestURI());
        reqRespLog.setReqMethod(request.getMethod());
        reqRespLog.setReqHeader(JSONUtil.toJsonStr(WebUtils.getHeader(request)));
        reqRespLog.setContentType(request.getContentType());
        reqRespLog.setReqBody(JSONUtil.toJsonStr(args));
        reqRespLog.setReqTime(new Date());
        reqRespLog.setClientIp(JakartaServletUtil.getClientIP(request));

        Object result = point.proceed(args);
        if (result != null) {
            reqRespLog.setRespBody(JSONUtil.toJsonStr(result));
        }
        reqRespLog.setRespTime(new Date());

        LogUtils.printLog(reqRespLog);

        return result;
    }
}
