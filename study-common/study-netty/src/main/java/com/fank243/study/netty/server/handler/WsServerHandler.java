package com.fank243.study.netty.server.handler;

import java.util.concurrent.ExecutorService;

import com.fank243.study.netty.constants.MessageReceiveEnum;
import com.fank243.study.netty.constants.NettyConstants;
import com.fank243.study.netty.factory.MessageFactory;
import com.fank243.study.netty.domain.model.MsgTypeEnum;
import com.fank243.study.netty.domain.model.NettyModel;
import com.fank243.study.netty.server.sender.WsSender;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import io.netty.channel.Channel;
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
 * @since 2021-05-03 01:12:26
 */
@Slf4j
public class WsServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ExecutorService executorService;

    public WsServerHandler() {
        executorService = ThreadUtil.newExecutor(Runtime.getRuntime().availableProcessors());
    }

    /** 与客户端成功建立连接时调用 **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        WsSender.saveChannel(ctx.channel());
        String msg =
            StrUtil.format("与客户端[{}]建立连接，分配通道ID：{}", channel.remoteAddress().toString(), channel.id().asLongText());
        log.debug("[WebSocket Server]{}", msg);
    }

    /** 与客户端断开连接时调用 **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        WsSender.removeChannelByChannelId(channel);
        String msg =
            StrUtil.format("与客户端[{}]断开连接，断连通道ID：{}", channel.remoteAddress().toString(), channel.id().asLongText());
        log.debug("[WebSocket Server]{}", msg);
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
            log.warn("[WebSocket Server]消息格式无法解析：{}", text);
            return;
        }

        MsgTypeEnum msgTypeEnum = EnumUtil.fromString(MsgTypeEnum.class, nettyModel.getMsgType().toUpperCase());
        if (msgTypeEnum == null) {
            System.out.println("[WebSocket Server]未识别的消息类型:" + nettyModel.getMessage());
            return;
        }

        Channel channel = ctx.channel();
        switch (msgTypeEnum) {
            case LOGIN:
                if (StrUtil.isNotEmpty(nettyModel.getFromUser())) {
                    WsSender.setChannel(channel, nettyModel.getFromUser());
                    log.debug("[WebSocket Server]用户[{}]绑定通道成功：{}", nettyModel.getFromUser(), channel.id().asLongText());
                }
                break;
            case LOGOUT:
                WsSender.removeChannel(nettyModel.getFromUser());
                log.debug("[WebSocket Server]用户[{}]解绑通道成功：{}", nettyModel.getFromUser(), channel.id().asLongText());
                break;

            default:
                executorService.submit(
                    () -> new MessageFactory().getWsInstance(MessageReceiveEnum.SYSTEM).receive(channel, nettyModel));
                break;
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
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent event) {
            // 监听读事件
            if (event.state().name().equalsIgnoreCase(IdleState.READER_IDLE.name())) {
                // send pong commend
                ctx.channel().writeAndFlush(new TextWebSocketFrame(NettyConstants.PONG));
            }
        }
    }

    /** 出现异常时调用 **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel channel = ctx.channel();
        String msg = StrUtil.format("与客户端[{}]出现异常，断连通道ID：{}，异常原因：{}", channel.remoteAddress().toString(),
            channel.id().asLongText(), cause.getLocalizedMessage());
        log.debug("[WebSocket Server]{}", msg, cause);
        channel.close();
    }
}
