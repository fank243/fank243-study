package com.github.fank243.study.core.web.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * OpenFeign 安全认证配置
 * 
 * @author FanWeiJie
 * @since 2022-09-26 10:40:01
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${study.security.feign.header.name}")
    private String securityFeignHeaderName;

    @Value("${study.security.feign.header.value}")
    private String securityFeignHeaderValue;

    @Override
    public void apply(RequestTemplate template) {
        template.header(securityFeignHeaderName, securityFeignHeaderValue);
    }
}
