package com.fank243.study.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fank243.study.common.core.service.RedisService;
import com.fank243.study.gateway.web.filter.ApiLogFilter;
import com.fank243.study.gateway.web.filter.SecurityFilter;
import com.fank243.study.gateway.web.filter.ValidateImageCodeFilter;
import com.fank243.study.gateway.web.handler.GatewayExceptionHandler;
import com.fank243.study.gateway.web.handler.ImageCodeHandler;
import com.fank243.study.gateway.web.handler.SentinelFallbackHandler;

import brave.Tracer;

/**
 * saToken注册统一鉴权
 *
 * @author FanWeiJie
 * @since 2022-05-11 10:46:17
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {
    @Bean
    public GatewayExceptionHandler gatewayExceptionHandler() {
        return new GatewayExceptionHandler();
    }

    @Bean
    public SentinelFallbackHandler sentinelFallbackHandler() {
        return new SentinelFallbackHandler();
    }

    @Bean
    public ImageCodeHandler imageCodeHandler(RedisService redisService) {
        return new ImageCodeHandler(redisService);
    }

    @Bean
    public ValidateImageCodeFilter validateImageCodeFilter(RedisService redisService) {
        return new ValidateImageCodeFilter(redisService);
    }

    @Bean
    public ApiLogFilter apiLogFilter(Tracer tracer) {
        return new ApiLogFilter(tracer);
    }

    @Bean
    public SecurityFilter securityFilter(RedisService redisService) {
        return new SecurityFilter(redisService);
    }

}
