package com.fank243.study.mp.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Nacos Configuration
 *
 * {@link RefreshScope} 动态刷新，用于在运行中监听并刷新配置中心下发的配置
 *
 * @author FanWeiJie
 * @date 2021-06-03 23:24:35
 */
@RefreshScope
@Configuration
public class NacosConfig {

    /** 此注解用于开启与ribbon的集成 **/
    // @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
