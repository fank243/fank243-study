package com.fank243.study.netty.bootstrap;

import com.fank243.study.netty.server.WsServer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * ApplicationListener
 * 
 * @author FanWeiJie
 * @date 2021-05-04 15:28:10
 */
@Component
public class NettyBootstrap implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // netty init
        nettyInit();
    }

    private void nettyInit() {
        new WsServer().start("0.0.0.0", 1900);
    }

}
