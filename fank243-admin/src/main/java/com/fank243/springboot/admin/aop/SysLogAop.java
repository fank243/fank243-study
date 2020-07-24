package com.fank243.springboot.admin.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.config.RequestWrapper;
import com.fank243.springboot.admin.model.ActiveUser;
import com.fank243.springboot.admin.service.logger.SysLogService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.admin.utils.ThreadUtils;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.SysLog;
import com.fank243.springboot.core.entity.logger.SysLogDO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Order(2)
public class SysLogAop {

    @Resource
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.fank243.springboot.core.annotation.SysLog)")
    public void point() {}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        try {
            Signature signature = joinPoint.getSignature();
            if (!(signature instanceof MethodSignature)) {
                return joinPoint.proceed();
            }
            MethodSignature methodSignature = (MethodSignature)signature;
            SysLog annotation = methodSignature.getMethod().getAnnotation(SysLog.class);
            if (annotation == null) {
                return joinPoint.proceed();
            }
            Date requestTime = new Date();

            ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return joinPoint.proceed();
            }
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();

            Map<String, String> headMap = WebUtils.getHeader(request);
            String headers = JSON.toJSONString(headMap);

            String body;
            if (RequestMethod.POST.name().equals(request.getMethod())) {
                Object[] paramsArray = joinPoint.getArgs();
                body = argsArrayToString(paramsArray);
            } else {
                Map<?, ?> paramsMap = (Map<?, ?>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                body = JSONObject.toJSONString(paramsMap);
            }

            proceed = joinPoint.proceed();
            if (!(proceed instanceof ResultInfo)) {
                return proceed;
            }
            Date responseTime = new Date();
            ResultInfo result = (ResultInfo)proceed;

            ActiveUser activeUser = ShiroUtils.getActiveUser();
            if (activeUser == null) {
                return proceed;
            }
            Long userId = activeUser.getUserId();

            if (response == null) {
                return joinPoint.proceed();
            }

            // 获取目标类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取方法名
            String methodName = joinPoint.getSignature().getName();

            result = addRecord(annotation, requestTime, responseTime, result, request, response, userId, className,
                methodName, headers, body);
            if (!result.isSuccess()) {
                log.warn(result.toString());
            }
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            // 移除Request ID
            ThreadUtils.remove();
        }
        return proceed;
    }

    private ResultInfo addRecord(SysLog annotation, Date requestTime, Date responseTime, ResultInfo result,
        HttpServletRequest request, HttpServletResponse response, Long userId, String className, String methodName,
        String headers, String body) {
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
        sysLog.setSessionId(request.getSession().getId());
        String requestId = ThreadUtils.get();
        sysLog.setRequestId(requestId);
        sysLog.setRequestHeader(headers);
        sysLog.setRequestBody(body);
        sysLog.setRequestTime(requestTime);
        sysLog.setResponseStatus(response.getStatus());
        sysLog.setResponseBody(result.toString());
        sysLog.setResponseTime(responseTime);
        sysLog.setResultCode(result.getCode());

        return sysLogService.addRecord(sysLog);
    }

    /**
     * 请求参数拼装
     *
     * @param paramsArray
     * @return
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                Object jsonObj = JSON.toJSON(paramsArray[i]);
                params += jsonObj.toString() + " ";
            }
        }
        return params.trim();
    }

}
