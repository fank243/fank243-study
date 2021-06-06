package com.fank243.study.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 
 * @author FanWeiJie
 * @date 2021-06-06 17:29:04
 */
@MapperScan(basePackages = {"com.fank243.study.mp.dao"})
@SpringBootApplication
public class MpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class, args);
    }
}
