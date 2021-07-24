package com.fank243.study.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author FanWeiJie
 * @since 2021-07-03 21:53:27
 */
@ComponentScan(basePackages = {"com.fank243.study"})
@SpringBootApplication
public class AdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }
}
