package com.fank243.study.sentinel.exception;

/**
 * Sentinel 限流通用处理
 *
 * @author FanWeiJie
 * @since 2021-06-05 20:41:21
 */
@SuppressWarnings("unused")
public class SentinelFallback {

    /**
     * 限流通用异常拦截
     *
     * 流量控制异常：{@link com.alibaba.csp.sentinel.slots.block.flow.FlowException}
     *
     * 服务降级异常：{@link com.alibaba.csp.sentinel.slots.block.degrade.DegradeException}
     *
     * @param throwable Throwable
     */
    public static String handlerException(Throwable throwable) {
        System.out.println(throwable.toString());
        return throwable.getMessage();
    }
}
