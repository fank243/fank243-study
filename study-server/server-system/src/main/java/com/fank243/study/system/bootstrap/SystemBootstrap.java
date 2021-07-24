package com.fank243.study.system.bootstrap;

import com.fank243.study.netty.properties.NettyProperties;
import com.fank243.study.netty.server.TcpServer;
import com.fank243.study.netty.server.WsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 启动完成后初始化
 * 
 * @author FanWeiJie
 * @since 2021-07-09 21:31:40
 */
@Slf4j
@Component
public class SystemBootstrap implements ApplicationListener<ApplicationStartedEvent> {

    private final NettyProperties nettyProperties;

    public SystemBootstrap(NettyProperties nettyProperties) {
        this.nettyProperties = nettyProperties;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
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
