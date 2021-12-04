package com.fank243.study.system.netty;

import org.springframework.stereotype.Component;

import com.fank243.study.netty.factory.IMessage;
import com.fank243.study.netty.protobuf.MessageProto;

import io.netty.channel.Channel;

/**
 * Netty TCP 消息接收处理器
 * 
 * @author FanWeiJie
 * @since 2021-07-19 15:47:59
 */
@Component
public class TcpMessage implements IMessage {

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
