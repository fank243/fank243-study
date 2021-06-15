package com.fank243.study.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统管理服务
 * 
 * @author FanWeiJie
 * @date 2021-06-08 23:32:33
 */
@ComponentScan(basePackages = {"com.fank243.study"})
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
