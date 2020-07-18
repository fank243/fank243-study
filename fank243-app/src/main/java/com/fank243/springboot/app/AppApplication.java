package com.fank243.springboot.app;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 启动类
 * 
 * @author FanWeiJie
 * @date 2020-03-20 20:53:37
 */
@EnableMethodCache(basePackages = {"com.fank243.springboot.app", "com.fank243.springboot.core"})
@EnableCreateCacheAnnotation
@EntityScan(basePackages = {"com.fank243.springboot.core"})
@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class);
    }
}
