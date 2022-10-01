package com.fank243.study.netty.factory;

import io.netty.channel.Channel;

/**
 * Netty 消息处理器接口
 * 
 * @author FanWeiJie
 * @since 2021-07-19 15:47:59
 */
public interface IMessage {

    /**
     * 接受消息
     * 
     * @param channel 通道
     * @param message 接收到的消息
     */
    void receive(Channel channel, Object message);
}
