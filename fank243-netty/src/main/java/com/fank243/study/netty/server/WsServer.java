package com.fank243.study.netty.server;

import com.fank243.study.netty.server.handler.WsServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket 服务端
 *
 * @author FanWeiJie
 * @date 2021-05-03 01:12:26
 */
@Slf4j
public class WsServer {

    /**
     * 独立线程启动
     * 
     * @param ip 绑定IP，如需不限定IP，可以使用“0.0.0.0”
     * @param port 绑定端口
     */
    public void start(String ip, int port) {
        new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, new SynchronousQueue<>(), r -> {
            Thread thread = new Thread(r);
            thread.setName("Netty-" + WsServer.class.getSimpleName());
            return thread;
        }).execute(() -> init(ip, port));
    }

    private void init(String ip, int port) {
        EventLoopGroup mainGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(mainGroup, workGroup)
            // @@formatter:off
                .handler(new LoggingHandler(LogLevel.DEBUG))
                // 重用地址
                .option(ChannelOption.SO_REUSEADDR, true)
                .channel(NioServerSocketChannel.class)
            // @@formatter:off
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 因为基于http协议，使用http的编码和解码器
                        socketChannel.pipeline().addLast(new HttpServerCodec());
                        // 是以块方式写，添加ChunkedWriteHandler处理器
                        socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                        /*
                         *  1. http数据在传输过程中是分段, HttpObjectAggregator ，就是可以将多个段聚合
                         *  2. 这就就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                         */
                        socketChannel.pipeline().addLast(new HttpObjectAggregator(8192));
                        // 这个类的核心功能就是将浏览器请求的HTTP协议转换为ws协议并保持长连接，101状态码就是用来区分的
                        socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                        // 注册定时心跳检测Handler，参数说明：读事件超时，写事件超时，读写事件超时，时间单位
                        socketChannel.pipeline().addLast(new IdleStateHandler(10,0,0,TimeUnit.SECONDS));
                        // 自定义消息处理Handler
                        socketChannel.pipeline().addLast(new WsServerHandler());
                    }
                });
            // 绑定IP端口并启动服务器
            ChannelFuture future = bootstrap.bind(new InetSocketAddress(ip, port)).sync();
            if(future.isSuccess()){
                log.info("[WebSocket Server]启动成功，监听IP端口信息：{}:{}",ip,port);
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mainGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
