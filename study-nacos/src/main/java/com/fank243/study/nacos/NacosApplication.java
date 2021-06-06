package com.fank243.study.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 * 
 * {@link EnableDiscoveryClient}
 * 服务注册与发现客户端，允许将自身注册到远程注册中心(nacos)，此处不加此注解，因为在其默认已开启，可通过application.yml属性“spring.cloud.discovery.enabled”开启或关闭
 * 
 * @author FanWeiJie
 * @date 2021-06-03 22:58:35
 */
@SpringBootApplication
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }
}
