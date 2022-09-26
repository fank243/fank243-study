package com.fank243.study.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.fank243.study.common.constants.Constants;

/**
 * 支撑服务启动类
 * 
 * @author FanWeiJie
 * @since 2022-09-26 15:09:37
 */
@EnableMethodCache(basePackages = {Constants.BASE_PACKAGE})
@EnableTransactionManagement
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@SpringBootApplication
public class SupportApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupportApplication.class, args);
    }
}
