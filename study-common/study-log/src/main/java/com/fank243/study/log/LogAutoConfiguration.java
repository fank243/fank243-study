package com.fank243.study.log;

import com.fank243.study.log.aspect.LogAspect;
import com.fank243.study.log.event.LogListener;
import com.fank243.study.support.service.ILogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.RequiredArgsConstructor;

/**
 * 日志配置类
 * 
 * @author FanWeiJie
 * @since 2022-10-04 23:37:56
 */
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    @Bean
    public LogListener sysLogListener(ILogService sysLogService) {
        return new LogListener(sysLogService);
    }

    @Bean
    public LogAspect sysLogAspect() {
        return new LogAspect();
    }

}
