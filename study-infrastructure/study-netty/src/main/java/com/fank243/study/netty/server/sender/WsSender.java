package com.fank243.study.netty.server.sender;

import cn.hutool.core.util.StrUtil;
import com.fank243.study.netty.domain.model.NettyModel;
import com.fank243.study.netty.utils.NettyUtils;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 消息发送器
 * 
 * @author FanWeiJie
 * @since 2021-05-04 15:05:34
 */
@SuppressWarnings("UnusedReturnValue")
@Slf4j
public class WsSender {

    /** 保存用于与通道关系 **/
    public static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();
    /** 保存通道会话ID **/
    public static final AttributeKey<String> SESSION = AttributeKey.valueOf("session");

    /**
     * 保存通道
     */
    public static void saveChannel(Channel channel) {
        channel.attr(SESSION).getAndSet(channel.id().asLongText());
    }

    /**
     * 上线一个用户
     */
    public static void setChannel(Channel channel, String userId) {
        channelMap.put(userId, channel);
    }

    /**
     * remove
     */
    public static void removeChannel(String userId) {
        Channel channel = channelMap.get(userId);
        if (channel != null) {
            channel.attr(SESSION).set(null);
        }
        channelMap.remove(userId);
    }

    /**
     * remove
     */
    public static void removeChannelByChannelId(Channel rChannel) {
        rChannel.attr(SESSION).set(null);
        for (Map.Entry<String, Channel> entry : channelMap.entrySet()) {
            Channel channel = entry.getValue();
            if (channel.id().asLongText().equalsIgnoreCase(rChannel.id().asLongText())) {
                channelMap.remove(entry.getKey());
            }
        }
    }

    /**
     * 发送消息
     * 
     * @param nettyModel 消息体
     */
    public static void sendMessage(NettyModel nettyModel) {
        if (StrUtil.isNotEmpty(nettyModel.getReceiveUser())) {
            NettyUtils.sendUnicastMessage(channelMap, SESSION, nettyModel);
        } else {
            NettyUtils.sendBroadcastMessage(channelMap, nettyModel);
        }
    }

    /**
     * 单播消息：指定通道发送
     *
     * @param channel 通道
     * @param nettyModel 消息体
     */
    public static void sendUnicastMessage(Channel channel, NettyModel nettyModel) {
        NettyUtils.sendUnicastMessage(channel, nettyModel);
    }
}
