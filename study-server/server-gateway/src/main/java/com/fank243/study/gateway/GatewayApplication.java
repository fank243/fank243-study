package com.fank243.study.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关服务
 * 
 * @author FanWeiJie
 * @since 2021-06-09 22:48:47
 */
@EnableFeignClients
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
//
//    @Bean
//    FeignRequestInterceptor requestInterceptor() {
//        return new FeignRequestInterceptor();
//    }
}
