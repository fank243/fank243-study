package com.fank243.study.gateway.utils;

import org.slf4j.MDC;

import com.fank243.study.support.domain.dto.ReqRespLogDTO;

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
     * @param logDTO 请求参数
     */
    public static void printLog(ReqRespLogDTO logDTO) {
        // 异步线程需要手动注入
        if (StrUtil.isNotBlank(logDTO.getTraceId())) {
            MDC.put("traceId", logDTO.getTraceId());
            MDC.put("spanId", logDTO.getSpanId());
        }
        if (log.isDebugEnabled()) {
            log.debug("请求拦截：{}", JSONUtil.toJsonStr(logDTO));
        }
    }

}
