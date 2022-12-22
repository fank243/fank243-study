package com.github.fank243.study.gateway;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.github.fank243.study.core.constants.Constants;

/**
 * 网关服务
 * 
 * @author FanWeiJie
 * @since 2021-06-09 22:48:47
 */
@MapperScan(basePackages = {Constants.BASE_PACKAGE_DAO})
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
