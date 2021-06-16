package com.fank243.study.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Demo
 * 
 * @author FanWeiJie
 * @since 2021-06-16 21:36:46
 */
@ComponentScan(basePackages = {"com.fank243.study"})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
