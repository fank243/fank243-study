package com.fank243.study.netty.bootstrap;

import com.fank243.study.netty.properties.NettyProperties;
import com.fank243.study.netty.server.TcpServer;
import com.fank243.study.netty.server.WsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * ApplicationListener
 * 
 * @author FanWeiJie
 * @date 2021-05-04 15:28:10
 */
@Slf4j
@Component
public class NettyBootstrap implements ApplicationListener<ApplicationReadyEvent> {

    private final NettyProperties nettyProperties;

    public NettyBootstrap(NettyProperties nettyProperties) {
        this.nettyProperties = nettyProperties;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // netty init
        nettyInit();
    }

    private void nettyInit() {
        if (nettyProperties.isServerWsEnable()) {
            new WsServer().start(nettyProperties.getServerWsIp(), nettyProperties.getServerWsPort());
        } else {
            log.debug("WebSocket Server NOt Enable...");
        }
        if (nettyProperties.isServerTcpEnable()) {
            new TcpServer().start(nettyProperties.getServerTcpIp(), nettyProperties.getServerTcpPort());
        } else {
            log.debug("TCP Server NOt Enable...");
        }
    }

}
