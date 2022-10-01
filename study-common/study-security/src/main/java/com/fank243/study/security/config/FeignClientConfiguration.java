package com.fank243.study.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

import com.fank243.study.security.interceptor.Oauth2RequestInterceptor;

import feign.RequestInterceptor;

/**
 * OpenFeign 配置
 * 
 * @author FanWeiJie
 * @since 2022-10-01 22:35:23
 */
public class FeignClientConfiguration {

    /**
     * 注入 oauth2 feign token 增强
     * 
     * @param tokenResolver token获取处理器
     * @return 拦截器
     */
    @Bean
    public RequestInterceptor oauthRequestInterceptor(BearerTokenResolver tokenResolver) {
        return new Oauth2RequestInterceptor(tokenResolver);
    }

}
