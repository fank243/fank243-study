package com.fank243.study.log.aspect;

import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.MediaType;

import com.fank243.study.common.core.utils.WebUtils;
import com.fank243.study.log.annotation.ApiLog;
import com.fank243.study.log.enums.LogTypeEnum;
import com.fank243.study.log.event.LogEvent;
import com.fank243.study.log.utils.LogUtils;
import com.fank243.study.support.domain.dto.LogDTO;

import brave.Span;
import brave.Tracer;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 操作日志使用spring event异步入库
 * 
 * @author FanWeiJie
 * @since 2022-10-04 23:39:01
 */
@Aspect
@Slf4j
public class LogAspect {

    @Resource
    private Tracer tracer;

    @Around("@annotation(apiLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, ApiLog apiLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        if (log.isDebugEnabled()) {
            log.debug("[系统日志入库]：调用类名及方法名：{}，{}", strClassName, strMethodName);
        }
        LogDTO logDTO = LogUtils.getSysLog();

        Object[] args = point.getArgs();
        MediaType mediaType = MediaType.parseMediaType(logDTO.getContentType());
        if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
            logDTO.setReqBody(StrUtil.trimToEmpty(JSONUtil.toJsonStr(args[0])));
        } else if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(mediaType)
            || MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)) {
            logDTO.setReqBody(JSONUtil.toJsonStr(WebUtils.getParams()));
        }

        Long startTime = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = point.proceed();
        } catch (Exception e) {
            logDTO.setLogType(LogTypeEnum.ERROR.getLogType());
            logDTO.setError(e.getMessage());
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            logDTO.setRespTime(new Date());
            logDTO.setExecuteTime(endTime - startTime);

            if (obj != null) {
                String objStr = Convert.toStr(obj, "");
                if (StrUtil.isNotBlank(objStr) && JSONUtil.isTypeJSON(objStr)) {
                    logDTO.setRespBody(objStr);
                }
            }

            Span span = tracer.currentSpan();
            if (span != null) {
                logDTO.setTraceId(span.context().traceIdString());
                logDTO.setSpanId(span.context().spanIdString());
            } else {
                logDTO.setTraceId("");
                logDTO.setSpanId("");
            }

            // 发布事件
            SpringUtil.publishEvent(new LogEvent(logDTO));
        }

        return obj;
    }

}
