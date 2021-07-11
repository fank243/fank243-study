package com.fank243.study.client.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author FanWeiJie
 * @since 2021-07-03 21:53:27
 */
@EnableRetry
@ComponentScan(basePackages = {"com.fank243.study"})
@SpringBootApplication
public class AdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }
}
