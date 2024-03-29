package com.github.fank243.study.system;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.fank243.study.core.constants.Constants;

/**
 * 系统服务
 * 
 * @author FanWeiJie
 * @since 2021-06-08 23:32:33
 */
@EnableRetry
@EnableTransactionManagement
@MapperScan(basePackages = {Constants.BASE_PACKAGE_MAPPER})
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
