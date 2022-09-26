package com.fank243.study.common.web.config;

import org.springframework.context.annotation.Configuration;

import com.fank243.study.common.constants.Constants;

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

    @Override
    public void apply(RequestTemplate template) {
        template.header(Constants.SECURITY_FEIGN_KEY, Constants.SECURITY_FEIGN_VALUE);
    }
}
