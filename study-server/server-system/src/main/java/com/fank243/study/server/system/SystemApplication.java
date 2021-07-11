package com.fank243.study.server.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 系统管理服务
 * 
 * @author FanWeiJie
 * @date 2021-06-08 23:32:33
 */
@EnableRetry
@ComponentScan(basePackages = {"com.fank243.study"})
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
