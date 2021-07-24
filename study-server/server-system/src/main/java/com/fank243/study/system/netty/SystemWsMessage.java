package com.fank243.study.system.netty;

import com.fank243.study.netty.factory.NettyMessage;
import com.fank243.study.netty.model.NettyModel;
import com.fank243.study.netty.server.sender.WsSender;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * Netty WS 消息接收处理器 - 系统管理
 * 
 * @author FanWeiJie
 * @since 2021-07-19 15:47:59
 */
@Component
public class SystemWsMessage implements NettyMessage {

    @Override
    public void receive(Channel channel, NettyModel nettyModel) {
        WsSender.sendUnicastMessage(channel, nettyModel);

        WsSender.sendMessage(nettyModel);
    }
}
