package com.fank243.study.netty.factory;

import cn.hutool.extra.spring.SpringUtil;
import com.fank243.study.netty.constants.MessageReceiveEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty 消息处理器工厂
 * 
 * @author FanWeiJie
 * @since 2021-07-19 15:47:05
 */
@Slf4j
public class NettyMessageFactory {

    /**
     * WS消息 获取消息处理器实例
     * 
     * @param receiveEnum 消息处理器枚举
     * @return 处理器实例
     */
    public NettyMessage getWsInstance(MessageReceiveEnum receiveEnum) {
        return getInstance("Ws", receiveEnum);
    }

    /**
     * TCP消息 获取消息处理器实例
     *
     * @param receiveEnum 消息处理器枚举
     * @return 处理器实例
     */
    public NettyMessage getTcpInstance(MessageReceiveEnum receiveEnum) {
        return getInstance("Tcp", receiveEnum);
    }

    private NettyMessage getInstance(String messageType, MessageReceiveEnum receiveEnum) {
        String clsName = getClassName(messageType, receiveEnum);
        NettyMessage message = null;
        try {
            message = SpringUtil.getBean(clsName);
        } catch (Exception e) {
            log.error("创建Netty消息处理器实例[{}]异常：{}", clsName, e.getLocalizedMessage(), e);
        }
        return message;
    }

    private String getClassName(String messageType, MessageReceiveEnum receiveEnum) {
        String clsName = "";
        switch (receiveEnum) {
            case SYSTEM:
                clsName = receiveEnum.name().toLowerCase() + messageType + "Message";
                break;

            case ADAPTER:
                break;

            default:
                break;
        }

        return clsName;
    }

}
