package com.fank243.study.gateway.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.MDC;
import org.springframework.http.server.reactive.ServerHttpRequest;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Log Tool
 * 
 * @author FanWeiJie
 * @since 2020-10-25 15:44:35
 */
@Slf4j
public class LogUtils {

    /**
     * 打印日志
     * 
     * @param request ServerHttpRequest
     * @param params 请求参数
     */
    public static void printLog(ServerHttpRequest request, String params) {
        printLog(request, params, "");
    }

    /**
     * 打印日志
     * 
     * @param request ServerHttpRequest
     * @param params 请求参数
     * @param response 响应参数
     */
    public static void printLog(ServerHttpRequest request, String params, String response) {
        printLog(request, params, response, "", "");
    }

    /**
     * 打印日志
     * 
     * @param request ServerHttpRequest
     * @param params 请求参数
     * @param traceId traceId
     * @param spanId spanId
     */
    public static void printLog(ServerHttpRequest request, String params, String response, String traceId,
        String spanId) {
        String uri = request.getURI().getPath();
        String method = request.getMethodValue();
        String ipAddr = Objects.requireNonNull(request.getRemoteAddress()).getHostString();

        Map<String, String> headerMap = new HashMap<>(request.getHeaders().entrySet().size());
        for (Map.Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
            headerMap.put(entry.getKey(), entry.getValue().get(0));
        }
        String headers = JSONUtil.toJsonStr(headerMap);

        String logMsg = String.format("请求响应拦截：%s，%s %s，header：%s，params：%s，response：%s", ipAddr, method, uri, headers,
            params, response);

        // 异步线程需要手动注入
        if (StrUtil.isNotBlank(traceId)) {
            MDC.put("traceId", traceId);
            MDC.put("spanId", spanId);
        }

        printLog(logMsg);
    }

    /**
     * 打印日志
     *
     * @param logMsg 请求参数
     */
    public static void printLog(String logMsg) {
        if (log.isDebugEnabled()) {
            log.debug(logMsg);
        }
    }

}
