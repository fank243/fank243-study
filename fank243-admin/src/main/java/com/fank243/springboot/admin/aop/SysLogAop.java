package com.fank243.springboot.admin.aop;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.fank243.springboot.admin.model.ActiveUser;
import com.fank243.springboot.admin.service.SysLogService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.Submit;
import com.fank243.springboot.core.annotation.SysLog;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.entity.SysLogDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
@Order(2)
public class SysLogAop {

    @Resource
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.fank243.springboot.core.annotation.SysLog)")
    public void point() {}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {

            sysLog(joinPoint);

            return joinPoint.proceed();
        } catch (Exception e) {
            log.error(e.toString());
        }
        return joinPoint.proceed();
    }

    private void sysLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return;
        }
        MethodSignature methodSignature = (MethodSignature)signature;
        SysLog annotation = methodSignature.getMethod().getAnnotation(SysLog.class);
        if (annotation == null) {
            return;
        }
        Object proceed = joinPoint.proceed();

        if (!(proceed instanceof ResultInfo)) {
            return;
        }
        ResultInfo result = (ResultInfo)proceed;

        HttpServletRequest request = WebUtils.getRequest();
        HttpServletResponse response = WebUtils.getResponse();

        ActiveUser activeUser = ShiroUtils.getActiveUser();
        if (activeUser == null) {
            return;
        }
        Long userId = activeUser.getUserId();

        // 获取目标类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();

        Map<String, String> header = WebUtils.getHeader(request);
        Map<String, String> body = WebUtils.getBody(request);
        String headers = JSON.toJSONString(header);
        String bodys = JSON.toJSONString(body);

        SysLogDO sysLog = new SysLogDO();

        sysLog.setAdminId(userId);
        sysLog.setLogLevel(annotation.logLevel());
        sysLog.setLogType(annotation.logType());
        sysLog.setLogDesc(annotation.desc());

        sysLog.setClassName(className);
        sysLog.setMethodName(methodName);

        sysLog.setRequestIp(WebUtils.getIp(request));
        sysLog.setRequestUri(request.getRequestURI());
        sysLog.setRequestMethod(request.getMethod());
        sysLog.setRequestId(request.getRequestedSessionId());
        sysLog.setRequestHeader(headers);
        sysLog.setRequestBody(bodys);
        sysLog.setResponseStatus(response.getStatus());
        sysLog.setResponseBody(result.toString());

        result = sysLogService.addRecord(sysLog);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
    }
}
