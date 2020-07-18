package com.fank243.springboot.admin;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 启动类
 *
 * @author FanWeiJie
 * @date 2020-03-07 21:58:02
 */
@EnableAsync
@EnableScheduling
@EnableWebMvc
@EnableTransactionManagement
@ServletComponentScan
@EnableMethodCache(basePackages = "com.fank243.springboot.admin")
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = {"com.fank243.springboot"})
@EntityScan(basePackages = {"com.fank243.springboot.core"})
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
