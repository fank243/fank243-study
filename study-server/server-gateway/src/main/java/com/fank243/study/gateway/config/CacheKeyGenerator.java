package com.fank243.study.gateway.config;

import java.lang.reflect.Method;

import cn.hutool.core.util.StrUtil;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

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
        for (int i = 0; i < params.length; i++) {
            Object obj = params[i];
            if (obj != null) {
                sb.append(obj).append("-");
            } else {
                sb.append("p").append(i).append("-");
            }
        }
        String str = sb.toString();
        return str.substring(0, str.lastIndexOf("-"));
    }
}
