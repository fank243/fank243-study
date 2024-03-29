package com.github.fank243.study.gateway.constants;

import com.github.fank243.study.gateway.web.filter.ApiLogFilter;
import com.github.fank243.study.gateway.web.filter.SecurityFilter;
import com.github.fank243.study.gateway.web.filter.ValidateImageCodeFilter;

/**
 * 拦截器拦截顺序
 *
 * @author FanWeiJie
 * @since 2022-03-11 15:42:41
 */
public class FilterOrderConstant {

    /**
     * 获取特定拦截器序号
     *
     * @param clsName 拦截器全限定类名
     * @return 序号
     */
    public static int getOrder(String clsName) {
        // 安全令牌拦截器
        if (SecurityFilter.class.getName().equalsIgnoreCase(clsName)) {
            return -100;
        }
        // Skywalking 链路追踪ID拦截处理器
        // if (TraceIdFilter.class.getName().equalsIgnoreCase(clsName)) {
        // return -99;
        // }
        // 请求参数拦截处理器
        else if (ApiLogFilter.class.getName().equalsIgnoreCase(clsName)) {
            return -98;
        }
        // 请求参数拦截处理器
        else if (ValidateImageCodeFilter.class.getName().equalsIgnoreCase(clsName)) {
            return -97;
        }
        return 0;
    }
}
