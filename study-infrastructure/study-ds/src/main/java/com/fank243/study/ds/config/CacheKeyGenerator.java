package com.fank243.study.ds.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.HashUtil;

/**
 * Spring Cache Key 生成策略
 *
 * @deprecated 已改用JetCache：<a href= "https://github.com/alibaba/jetcache/blob/master/introduce_CN.md">JetCache</a>
 * @author FanWeiJie
 * @since 2022-05-11 16:21:08
 */
@Deprecated
@Component
public class CacheKeyGenerator implements KeyGenerator {

    @NotNull
    @Override
    public Object generate(@NotNull Object target, @NotNull Method method, Object... params) {
        if (params.length == 0) {
            return HashUtil.oneByOneHash("");
        }
        return HashUtil.oneByOneHash(ClassUtil.getClass(target).getName() + method.getName() + Arrays.toString(params))
            & Integer.MAX_VALUE;
    }
}
