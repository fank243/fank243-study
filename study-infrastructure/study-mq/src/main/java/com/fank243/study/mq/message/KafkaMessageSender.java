package com.fank243.study.mq.message;

import com.fank243.study.mq.config.StudyChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * kafka 消息发送器，每个通道对应一个kafka的主题。
 *
 * @author FanWeiJie
 * @since 2021-09-04 16:04:51
 */
@Slf4j
@Component
public class KafkaMessageSender {

    @Resource
    private StudyChannel studyChannel;

    /**
     * 消息发送到默认通道：缺省通道对应缺省主题
     * 
     * @param message 消息
     */
    public void sendToDefaultChannel(String message) {
        studyChannel.sendDefaultMsg().send(MessageBuilder.withPayload(message).build());
    }

    /**
     * 消息发送到告警通道：告警通道对应告警主题
     * 
     * @param message 消息
     */
    public void sendToAlertChannel(String message) {
        studyChannel.sendAlertMsg().send(MessageBuilder.withPayload(message).build());
    }
}
