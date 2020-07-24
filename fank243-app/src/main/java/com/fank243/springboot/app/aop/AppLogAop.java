package com.fank243.springboot.app.aop;

import com.alibaba.fastjson.JSON;
import com.fank243.springboot.app.annotation.Login;
import com.fank243.springboot.app.model.ActiveUser;
import com.fank243.springboot.app.model.AppLogContext;
import com.fank243.springboot.app.model.AppRequest;
import com.fank243.springboot.app.service.AppLogService;
import com.fank243.springboot.app.utils.ThreadUtils;
import com.fank243.springboot.app.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.AppLog;
import com.fank243.springboot.core.entity.logger.AppLogDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 业务日志
 *
 * @author FanWeiJie
 * @date 2020-04-11 21:36:10
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class AppLogAop {

    @Resource
    private AppLogService appLogService;

    @Pointcut("@annotation(com.fank243.springboot.core.annotation.AppLog) && @annotation(com.fank243.springboot.app.annotation.Login)")
    public void point() {}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return appLog(joinPoint);
        } catch (Exception e) {
            log.error(e.toString());
        }
        return null;
    }

    private Object appLog(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return joinPoint.proceed();
        }
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        AppLog annotation = method.getAnnotation(AppLog.class);
        if (annotation == null) {
            return joinPoint.proceed();
        }
        Login login = method.getAnnotation(Login.class);
        if (login == null) {
            return joinPoint.proceed();
        }

        // 请求
        Date requestTime = new Date();
        Object[] args = joinPoint.getArgs();
        if (!(args[0] instanceof AppRequest)) {
            return joinPoint.proceed();
        }
        AppRequest appRequest = (AppRequest)args[0];

        // 响应
        Object proceed = joinPoint.proceed();
        Date responseTime = new Date();
        if (!(proceed instanceof ResultInfo)) {
            return proceed;
        }
        ResultInfo result = (ResultInfo)proceed;

        // 获取目标类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();

        Map<String, String> header = WebUtils.getHeader(request);
        String headers = JSON.toJSONString(header);

        ActiveUser activeUser = (ActiveUser)SecurityUtils.getSubject().getPrincipal();
        if (activeUser == null) {
            return proceed;
        }

        AppLogDO appLog = new AppLogDO();

        appLog.setUserId(activeUser.getUserId());
        appLog.setLogLevel(annotation.logLevel());
        appLog.setLogType(annotation.logType());
        appLog.setLogDesc(annotation.desc());

        appLog.setClassName(className);
        appLog.setMethodName(methodName);

        appLog.setDeviceType(appRequest.getDeviceType());
        appLog.setDeviceNumber(appRequest.getDeviceNumber());

        AppLogContext appLogContext = ThreadUtils.get();
        appLog.setSessionId(appLogContext.getSessionId());
        appLog.setRequestId(appLogContext.getRequestId());
        appLog.setRequestIp(WebUtils.getIp(request));
        appLog.setRequestUri(request.getRequestURI());
        appLog.setRequestMethod(request.getMethod());
        appLog.setRequestHeader(headers);
        appLog.setRequestBody(appRequest.getPayload());
        appLog.setRequestTime(requestTime);
        appLog.setResponseStatus(response.getStatus());
        appLog.setResponseBody(result.toString());
        appLog.setResponseTime(responseTime);

        appLog.setResultCode(result.getCode());

        result = appLogService.addRecord(appLog);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }

        // 移除Request ID
        ThreadUtils.remove();
        return proceed;
    }
}
