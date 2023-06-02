package com.github.fank243.study.core.config;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Error Properties 配置
 *
 * @author FanWeiJie
 * @since 2022-10-05 20:35:37
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = SERVLET)
public class DefaultErrorConfiguration {

    @Value("${error.path:/error}")
    private String path = "/error";

    @Bean
    public ErrorProperties errorProperties() {
        ErrorProperties errorProperties = new ErrorProperties();
        errorProperties.setPath(this.path);
        return errorProperties;
    }
}
