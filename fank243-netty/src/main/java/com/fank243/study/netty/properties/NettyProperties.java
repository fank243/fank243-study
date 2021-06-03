package com.fank243.study.netty.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Netty Properties
 * 
 * @author FanWeiJie
 * @date 2021-06-03 21:52:21
 */
@Data
@Component
public class NettyProperties {

    @Value("${fank243.netty.server.ws.enable:false}")
    private boolean serverWsEnable;

    @Value("${fank243.netty.server.ws.ip:0.0.0.0}")
    private String serverWsIp;

    @Value("${fank243.netty.server.ws.port:9000}")
    private int serverWsPort;

    @Value("${fank243.netty.server.tcp.enable:false}")
    private boolean serverTcpEnable ;

    @Value("${fank243.netty.server.tcp.ip:0.0.0.0}")
    private String serverTcpIp ;

    @Value("${fank243.netty.server.tcp.port:9001}")
    private int serverTcpPort;
}
