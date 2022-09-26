package com.fank243.study.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.fank243.study.common.constants.Constants;

/**
 * 网关服务
 * 
 * @author FanWeiJie
 * @since 2021-06-09 22:48:47
 */
@EnableMethodCache(basePackages = Constants.BASE_PACKAGE)
@EnableCaching
@ComponentScan(basePackages = {Constants.BASE_PACKAGE_GATEWAY, Constants.BASE_PACKAGE_DS,
    Constants.BASE_PACKAGE_COMMON + ".web.config"})
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
