package com.fank243.study.core.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Open Feign 配置
 * 
 * @author FanWeiJie
 * @date 2021-06-08 23:50:34
 */
@EnableFeignClients(basePackages = {"com.fank243.study"})
@Configuration
public class FeignConfig {

    /** 日志级别 **/
    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new DefaultErrorDecoder();
//    }

    @Bean
    public Retryer retryer() {
        // 重试间隔：2s，最大重试间隔：4s，最多重试次数：5
        return new Retryer.Default(2000, 4000, 5);
    }
}
