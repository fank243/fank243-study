package com.fank243.study.netty.server.sender;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fank243.study.netty.model.NettyModel;
import io.netty.channel.Channel;
import com.fank243.study.netty.utils.ChannelUtils;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 消息发送器
 * 
 * @author FanWeiJie
 * @date 2021-05-04 15:05:34
 */
@Slf4j
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
        String msg = JSONUtil.toJsonStr(nettyModel);
        System.out.println("[WebSocket Server]发送消息：" + msg);
        if (StrUtil.isNotEmpty(nettyModel.getReceiveUser())) {
            Channel channel = ChannelUtils.getChannel(nettyModel.getReceiveUser());
            if (ChannelUtils.isOnline(channel)) {
                channel.writeAndFlush(new TextWebSocketFrame(msg));
            } else {
                System.out.println("[WebSocket Server]接收用户不在线：" + msg);
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
                channel.writeAndFlush(new TextWebSocketFrame(msg));
            }
        }
    }
}
