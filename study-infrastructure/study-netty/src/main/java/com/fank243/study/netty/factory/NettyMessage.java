package com.fank243.study.netty.factory;

import com.fank243.study.netty.model.NettyModel;
import io.netty.channel.Channel;

/**
 * Netty 消息处理器接口
 * 
 * @author FanWeiJie
 * @since 2021-07-19 15:47:59
 */
public interface NettyMessage {

    /**
     * 接受消息
     * 
     * @param channel 通道
     * @param nettyModel 接收到的消息
     */
    void receive(Channel channel, NettyModel nettyModel);
}
