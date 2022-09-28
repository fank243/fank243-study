package com.fank243.study.system;

import com.fank243.study.common.constants.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alicp.jetcache.anno.config.EnableMethodCache;

/**
 * 系统服务
 * 
 * @author FanWeiJie
 * @since 2021-06-08 23:32:33
 */
@EnableRetry
@EnableMethodCache(basePackages = {Constants.BASE_PACKAGE})
@EnableTransactionManagement
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
