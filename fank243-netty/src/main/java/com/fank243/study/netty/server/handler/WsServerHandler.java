package com.fank243.study.netty.server.handler;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.fank243.study.netty.common.NettyConstants;
import com.fank243.study.netty.model.MsgTypeEnum;
import com.fank243.study.netty.model.NettyModel;
import com.fank243.study.netty.utils.ChannelUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket 服务端消息处理器
 *
 * @author FanWeiJie
 * @date 2021-05-03 01:12:26
 */
@Slf4j
public class WsServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /** 与客户端成功建立连接时调用 **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[WebSocket Server]与客户端连接成功：" + ctx.channel().remoteAddress().toString());
        ChannelUtils.saveChannel(ctx.channel());
        System.out.println("[WebSocket Server]当前已保存的通道数:" + ChannelUtils.channelCount);
    }

    /** 与客户端断开连接时调用 **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[WebSocket Server]与客户端断开连接：" + ctx.channel().remoteAddress().toString());
        ChannelUtils.removeChannelByChannelId(ctx.channel());
        System.out.println("[WebSocket Server]当前已保存的通道数:" + ChannelUtils.channelCount);
    }

    /** 接收到客户端消息时调用，如果客户端发送的消息无法解析时不调用 **/
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        System.out.println("[WebSocket Server]接收到消息：" + text);
        if (StringUtil.isNullOrEmpty(text)) {
            return;
        }

        // ping commend
        if (NettyConstants.PING.equalsIgnoreCase(text)) {
            return;
        }

        NettyModel nettyModel;
        try {
            nettyModel = JSONUtil.toBean(text, NettyModel.class);
        } catch (JSONException e) {
            return;
        }

        MsgTypeEnum msgTypeEnum = EnumUtil.fromString(MsgTypeEnum.class, nettyModel.getMsgType());
        if (msgTypeEnum == null) {
            System.out.println("[WebSocket Server]未识别的消息类型:" + nettyModel.getMessage());
            return;
        }
        switch (msgTypeEnum) {
            case LOGIN:
                if (StrUtil.isNotEmpty(nettyModel.getFromUser())) {
                    ChannelUtils.setChannel(ctx.channel(), nettyModel.getFromUser());
                }
                break;
            case LOGOUT:
                ChannelUtils.removeChannel(nettyModel.getFromUser());
                break;

            case LOG:
                ctx.channel().writeAndFlush(new TextWebSocketFrame(text));
                break;

            default:
        }
    }

    /**
     * 客户端与服务端交互事件触发器，比如服务端握手事件{@link WebSocketServerProtocolHandler.ServerHandshakeStateEvent}、握手完成事件{@link WebSocketServerProtocolHandler.HandshakeComplete}
     * <p>
     * 因此我们可以在这里自定义一些事件，比如与客户端的心跳检测事件{@link IdleStateEvent}，对于已经断开的客户端直接踢出去(一般客户端主动断开连接都会被{@code channelInactive(ctx)}监听到，此方法则会中止执行)
     * <p>
     * 如果需要注册{@link IdleStateEvent}，需要先在服务器启动时注册{@link IdleStateHandler}
     * <p>
     * 由于我方是服务端，因此此处我们只需要监听{@link IdleState#READER_IDLE}超时事件
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            // 监听读事件
            if (event.state().name().equalsIgnoreCase(IdleState.READER_IDLE.name())) {
                // send pong commend
                ctx.channel().writeAndFlush(new TextWebSocketFrame(NettyConstants.PONG));
            }
        }
    }

    /** 出现异常时调用 **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("[WebSocket Server]出现异常：{}", cause.toString());
    }
}
