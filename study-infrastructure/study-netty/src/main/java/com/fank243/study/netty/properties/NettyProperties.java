package com.fank243.study.netty.properties;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Netty Properties
 * 
 * @author FanWeiJie
 * @since 2021-06-03 21:52:21
 */
@Data
@Component
public class NettyProperties {

    @Value("${study.netty.server.ws.enable:false}")
    private boolean serverWsEnable;

    @Value("${study.netty.server.ws.ip:0.0.0.0}")
    private String serverWsIp;

    @Value("${study.netty.server.ws.port:9000}")
    private int serverWsPort;

    @Value("${study.netty.server.tcp.enable:false}")
    private boolean serverTcpEnable;

    @Value("${study.netty.server.tcp.ip:0.0.0.0}")
    private String serverTcpIp;

    @Value("${study.netty.server.tcp.port:9001}")
    private int serverTcpPort;

    public String getServerWsIp() {
        return StrUtil.trim(this.serverTcpIp);
    }

    public String getServerTcpIp() {
        return StrUtil.trim(this.serverTcpIp);
    }
}
