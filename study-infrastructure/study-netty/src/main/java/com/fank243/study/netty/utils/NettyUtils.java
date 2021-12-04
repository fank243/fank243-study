package com.fank243.study.netty.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fank243.study.netty.model.NettyModel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Channel 工具
 * 
 * @author FanWeiJie
 * @since 2021-05-04 13:34:51
 */
@Slf4j
public class NettyUtils {

    /**
     * 判断某个用户是否在线
     * 
     * @return 是否在线
     */
    public static boolean isOnline(AttributeKey<String> session, Channel channel) {
        if (channel == null) {
            return false;
        }
        return channel.attr(session).get() != null;
    }

    /**
     * 获取绑定的用户ID
     * 
     * @return 用户ID
     */
    public static String getUserId(ConcurrentHashMap<String, Channel> channelMap, Channel channel) {
        if (!channelMap.containsValue(channel)) {
            return null;
        }
        for (Map.Entry<String, Channel> entry : channelMap.entrySet()) {
            if (entry.getValue().id().asLongText().equalsIgnoreCase(channel.id().asLongText())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 发送广播消息，向所有已经进行了登录指令并成功绑定了通道的客户端发送消息
     */
    public static void sendBroadcastMessage(ConcurrentHashMap<String, Channel> channelMap, NettyModel nettyModel) {
        if (MapUtil.isEmpty(channelMap)) {
            System.out.println("发送广播消息时，至少应该存在一个登录在线的用户");
            return;
        }
        for (Map.Entry<String, Channel> entry : channelMap.entrySet()) {
            Channel channel = entry.getValue();
            if (channel != null) {
                sendMessage(channelMap, channel, nettyModel);
            }
        }
    }

    /**
     * 单播消息：指定用户发送
     *
     * @param nettyModel 消息体
     */
    public static void sendUnicastMessage(ConcurrentHashMap<String, Channel> channelMap, AttributeKey<String> session,
        NettyModel nettyModel) {
        if (StrUtil.isEmpty(nettyModel.getReceiveUser())) {
            System.out.println("发送单播消息时[receiveUser]字段不能为空：" + nettyModel);
            return;
        }

        Channel channel = channelMap.get(nettyModel.getReceiveUser());
        if (!isOnline(session, channel)) {
            System.out.println("发送单播消息失败，用户不在线：" + nettyModel.getReceiveUser());
            return;
        }
        sendMessage(channelMap, channel, nettyModel);
    }

    /**
     * 单播消息：指定通道发送
     *
     * @param channel 通道
     * @param nettyModel 消息体
     */
    public static void sendUnicastMessage(Channel channel, NettyModel nettyModel) {
        if (channel == null) {
            System.out.println("发送单播消息时，Channel不能为null");
            return;
        }
        sendMessage(null, channel, nettyModel);
    }

    private static void sendMessage(ConcurrentHashMap<String, Channel> channelMap, Channel channel,
        NettyModel nettyModel) {
        String msg = JSONUtil.toJsonStr(nettyModel);
        ChannelFuture channelFuture = channel.writeAndFlush(new TextWebSocketFrame(msg));
        channelFuture.addListener(future -> {
            if (!future.isSuccess()) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress)channel.remoteAddress();
                String hostname = inetSocketAddress.getHostName();
                int port = inetSocketAddress.getPort();
                String userId = (channelMap != null && !channelMap.isEmpty()) ? getUserId(channelMap, channel) : "";
                String errMsg = StrUtil.format("客户端ID：{}，主机及端口：{}:{}", userId, hostname, port);
                log.warn("发送消息失败：{}", errMsg);
                channel.close();
            }
            System.out.println("发送消息成功：" + msg);
        });
    }
}
