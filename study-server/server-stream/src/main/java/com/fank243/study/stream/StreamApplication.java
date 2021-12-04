package com.fank243.study.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

import com.fank243.study.mq.config.StudyChannel;

/**
 * 消息服务
 * 
 * @author FanWeiJie
 * @since 2021-06-08 23:32:33
 */
@ComponentScan(basePackages = {"com.fank243.study"})
@EnableBinding(StudyChannel.class)
@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamApplication.class, args);
    }

}
