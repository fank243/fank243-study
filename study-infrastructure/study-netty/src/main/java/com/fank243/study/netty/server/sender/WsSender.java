package com.fank243.study.netty.server.sender;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fank243.study.netty.model.NettyModel;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket 消息发送器
 * 
 * @author FanWeiJie
 * @date 2021-05-04 15:05:34
 */
@Slf4j
public class WsSender {

    /** 保存用于与通道关系 **/
    public static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();
    /** 保存通道会话ID **/
    public static final AttributeKey<String> SESSION = AttributeKey.valueOf("session");
    /** 保存通道会话计数 **/
    public static AtomicInteger channelCount = new AtomicInteger(0);

    /**
     * 保存通道
     */
    public static void saveChannel(Channel channel) {
        channel.attr(SESSION).getAndSet(channel.id().asLongText());
        channelCount.incrementAndGet();
    }

    /**
     * 上线一个用户
     */
    public static void setChannel(Channel channel, String userId) {
        channelMap.put(userId, channel);
    }

    /**
     * 根据用户ID获取该用户的通道
     */
    public static Channel getChannel(String userId) {
        return channelMap.get(userId);
    }

    /**
     * 判断某个用户是否在线
     */
    public static boolean isOnline(Channel channel) {
        if (channel == null) {
            return false;
        }
        return channel.attr(SESSION).get() != null;
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
        channelCount.decrementAndGet();
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
                channelCount.decrementAndGet();
            }
        }
    }

    /**
     * 发送消息
     */
    public static void sendMessage(NettyModel nettyModel) {
        String msg = JSONUtil.toJsonStr(nettyModel);
        System.out.println("[WebSocket Server]发送消息：" + msg);
        if (StrUtil.isNotEmpty(nettyModel.getReceiveUser())) {
            Channel channel = getChannel(nettyModel.getReceiveUser());
            if (isOnline(channel)) {
                channel.writeAndFlush(new TextWebSocketFrame(msg));
            } else {
                System.out.println("[WebSocket Server]接收用户不在线：" + msg);
            }
            return;
        }
        if (MapUtil.isEmpty(channelMap)) {
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
