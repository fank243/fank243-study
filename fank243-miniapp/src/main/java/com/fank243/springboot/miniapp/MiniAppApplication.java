package com.fank243.springboot.miniapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 启动类
 *
 * @author FanWeiJie
 * @date 2020-03-20 20:53:37
 */
@EntityScan(basePackages = {"com.fank243.springboot.core"})
@SpringBootApplication
public class MiniAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniAppApplication.class);
    }
}
