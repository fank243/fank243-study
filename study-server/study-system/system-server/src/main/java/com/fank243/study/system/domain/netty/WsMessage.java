package com.fank243.study.system.domain.netty;

import org.springframework.stereotype.Component;

import com.fank243.study.netty.factory.IMessage;
import com.fank243.study.netty.domain.model.NettyModel;
import com.fank243.study.netty.server.sender.WsSender;

import io.netty.channel.Channel;

/**
 * Netty WS 消息接收处理器
 * 
 * @author FanWeiJie
 * @since 2021-07-19 15:47:59
 */
@Component
public class WsMessage implements IMessage {

    @Override
    public void receive(Channel channel, Object msg) {
        NettyModel nettyModel = (NettyModel)msg;
        WsSender.sendUnicastMessage(channel, nettyModel);

        WsSender.sendMessage(nettyModel);
    }
}
