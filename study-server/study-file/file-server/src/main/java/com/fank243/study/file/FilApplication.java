package com.fank243.study.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.fank243.study.common.core.constants.Constants;

/**
 * 文件服务
 * 
 * @author FanWeiJie
 * @since 2022-09-28 13:47:22
 */
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@EnableTransactionManagement
@EnableConfigurationProperties
@SpringBootApplication
public class FilApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilApplication.class, args);
    }
}
