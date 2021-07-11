package com.fank243.study.server.system.bootstrap;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 启动完成后初始化
 * 
 * @author FanWeiJie
 * @since 2021-07-09 21:31:40
 */
@Component
public class SystemBootstrap implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

    }

}
