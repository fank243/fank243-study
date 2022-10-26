package com.github.fank243.study.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.fank243.study.core.constants.Constants;

/**
 * 支撑服务
 * 
 * @author FanWeiJie
 * @since 2022-09-26 15:09:37
 */
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@EnableTransactionManagement
@SpringBootApplication
public class SupportApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupportApplication.class, args);
    }
}
