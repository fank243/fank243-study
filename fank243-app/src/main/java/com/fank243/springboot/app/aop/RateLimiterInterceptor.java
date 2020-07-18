package com.fank243.springboot.app.aop;

import com.fank243.springboot.app.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.ApiLimit;
import com.fank243.springboot.core.annotation.Interceptor;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 限流拦截器
 * 
 * 全局优先级：1
 * 
 * @author FanWeiJie
 * @date 2019-12-14 15:18:38
 */
@Slf4j
@Interceptor(include = {"/app/**"}, order = 0)
public class RateLimiterInterceptor implements HandlerInterceptor {

    /** 缓存每次请求计数，缓存KEY就是limitType，每日自动清理，缓存个数最大：1000个 */
    private static LoadingCache<String, RateLimiter> caches =
        CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<>() {
            @Override
            public RateLimiter load(String key) {
                // 默认不限流
                return RateLimiter.create(Double.MAX_VALUE);
            }
        });

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HandlerMethod handlerMethod = null;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod)handler;
        }
        if (handlerMethod == null) {
            WebUtils.printJson2(response, ResultInfo.notFund());
            return false;
        }
        Method method = handlerMethod.getMethod();

        // 不需要限流
        if (!method.isAnnotationPresent(ApiLimit.class)) {
            return true;
        }

        ApiLimit apiLimit = method.getAnnotation(ApiLimit.class);
        if (apiLimit == null) {
            return true;
        }

        String key;
        // 获取限流策略
        ApiLimit.LimitType limitType = apiLimit.limitType();
        if (limitType.equals(ApiLimit.LimitType.IP)) {
            key = WebUtils.getIp(request) + "_" + request.getRequestURI();
        } else {
            key = request.getParameter("deviceNumber") + "_" + request.getRequestURI();
        }

        // 如果获取key失败，则不限流
        if (StringUtils.isBlank(key)) {
            return true;
        }

        try {
            RateLimiter rateLimiter = caches.get(key);
            // 重置令牌个数
            rateLimiter.setRate(apiLimit.perSecond());
            // 用于判断本次请求是否获得了许可，如果获得了许可则会返回true，否则返回false
            if (!rateLimiter.tryAcquire()) {
                if (log.isDebugEnabled()) {
                    log.debug("Api请求限流策略生效：{},{}", request.getRequestURI(), key);
                }
                WebUtils.printJson2(response, ResultInfo.fail("亲，您点的快了点"));
                return false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return true;
    }
}
