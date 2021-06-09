package com.fank243.study.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Api 服务
 * 
 * @author FanWeiJie
 * @date 2021-06-08 20:46:18
 */
@EnableFeignClients(basePackages = {"com.fank243.study.api.feign"})
@ComponentScan(basePackages = {"com.fank243.study"})
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
