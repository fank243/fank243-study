package com.fank243.study.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 系统管理服务
 * 
 * @author FanWeiJie
 * @since 2021-06-08 23:32:33
 */
@EnableRetry
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.fank243.study"})
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
