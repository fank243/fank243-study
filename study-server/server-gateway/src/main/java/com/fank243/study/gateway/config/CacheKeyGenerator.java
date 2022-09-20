package com.fank243.study.gateway.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import com.fank243.study.common.constants.StudyConstants;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.HashUtil;

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
            return HashUtil.oneByOneHash(StudyConstants.EMPTY);
        }
        return HashUtil.oneByOneHash(ClassUtil.getClass(target).getName() + method.getName() + Arrays.toString(params))
            & Integer.MAX_VALUE;
    }
}
