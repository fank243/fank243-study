package com.github.fank243.study.gateway.utils;

import org.slf4j.MDC;

import com.github.fank243.study.gateway.domain.ReqRespLogDTO;

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
     * @param respLogDTO 请求参数
     */
    public static void printLog(ReqRespLogDTO respLogDTO) {
        // 异步线程需要手动注入
        if (StrUtil.isNotBlank(respLogDTO.getTraceId())) {
            MDC.put("traceId", respLogDTO.getTraceId());
            MDC.put("spanId", respLogDTO.getSpanId());
        }
        if (log.isDebugEnabled()) {
            log.debug("请求拦截：{}", JSONUtil.toJsonStr(respLogDTO));
        }
    }

}
