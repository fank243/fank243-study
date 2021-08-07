package com.fank243.study.netty.client;

import com.fank243.study.netty.client.handler.TcpClientHandler;
import com.fank243.study.netty.protobuf.MessageProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TCP 客户端
 *
 * @author FanWeiJie
 * @date 2021-05-03 01:12:26
 */
@Slf4j
public class TcpClient {

    /**
     * 独立线程启动
     *
     * @param ip 服务端IP地址
     * @param port 绑定端口
     */
    public void start(String ip, int port) {
        new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, new SynchronousQueue<>(), r -> {
            Thread thread = new Thread(r);
            thread.setName("Netty-" + TcpClient.class.getSimpleName());
            return thread;
        }).execute(() -> init(ip, port));
        init(ip,port);
    }

    private void init(String ip, int port) {
        EventLoopGroup group  = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group )
            // @@formatter:off
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioSocketChannel.class)
                    // @@formatter:off
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new StringDecoder());
//                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast("encoder",new ProtobufEncoder());
                            socketChannel.pipeline().addLast("decoder",new ProtobufDecoder(MessageProto.Netty.getDefaultInstance()));
                            // 注册定时心跳检测Handler，参数说明：读事件超时，写事件超时，读写事件超时，时间单位
                            socketChannel.pipeline().addLast(new IdleStateHandler(10,0,0,TimeUnit.SECONDS));
                            // 自定义消息处理Handler
                                socketChannel.pipeline().addLast(new TcpClientHandler());
                        }
                    });
            // 绑定IP端口并启动服务器
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(ip, port)).sync();
            if(future.isSuccess()){
                log.info("[TCP Client]连接服务端成功：{}:{}",ip,port);
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
