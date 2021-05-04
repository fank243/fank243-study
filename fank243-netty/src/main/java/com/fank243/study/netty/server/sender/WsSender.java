package com.fank243.study.netty.server.sender;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fank243.study.netty.model.NettyModel;
import com.fank243.study.netty.utils.ChannelUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 消息发送器
 * 
 * @author FanWeiJie
 * @date 2021-05-04 15:05:34
 */
public class WsSender {

    private static final WsSender INSTANCE = new WsSender();

    public static WsSender getInstance() {
        return INSTANCE;
    }

    private WsSender() {}

    /**
     * 发送消息
     */
    public void sendMessage(NettyModel nettyModel) {
        if (StrUtil.isNotEmpty(nettyModel.getReceiveUser())) {
            Channel channel = ChannelUtils.getChannel(nettyModel.getReceiveUser());
            if (channel != null) {
                channel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(nettyModel)));
            }
            return;
        }
        ConcurrentHashMap<String, Channel> channelMap = ChannelUtils.channelMap;
        if (CollUtil.isEmpty(channelMap)) {
            return;
        }
        for (Map.Entry<String, Channel> entry : channelMap.entrySet()) {
            Channel channel = entry.getValue();
            if (channel != null) {
                channel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(nettyModel)));
            }
        }
    }
}
