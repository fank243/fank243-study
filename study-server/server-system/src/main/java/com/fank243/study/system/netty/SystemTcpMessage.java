package com.fank243.study.system.netty;

import com.fank243.study.netty.factory.NettyMessage;
import com.fank243.study.netty.protobuf.MessageProto;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * Netty TCP 消息接收处理器 - 系统管理
 * 
 * @author FanWeiJie
 * @since 2021-07-19 15:47:59
 */
@Component
public class SystemTcpMessage implements NettyMessage {

    @Override
    public void receive(Channel channel, Object message) {
        MessageProto.Netty messageProto = (MessageProto.Netty)message;
        MessageProto.Netty.MsgType msgType = messageProto.getMsgType();
        switch (msgType) {
            case Log:
                break;

            default:
        }
    }
}
