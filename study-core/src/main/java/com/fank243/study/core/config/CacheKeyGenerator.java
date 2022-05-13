package com.fank243.study.core.config;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;

/**
 * Cache Key Generator
 * 
 * @author FanWeiJie
 * @since 2022-05-11 16:21:08
 */
@Component
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (params.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : params) {
            if (obj != null) {
                sb.append(obj).append("-");
            } else {
                sb.append(StrUtil.uuid()).append("-");
            }
        }
        String str = sb.toString();
        return str.substring(0, str.lastIndexOf("-"));
    }
}
