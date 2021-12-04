package com.fank243.study.mq.message;

import cn.hutool.core.date.DateUtil;
import com.fank243.study.mq.config.StudyChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * kafka 从不同的通道实现消息的订阅
 * 
 * @author FanWeiJie
 * @since 2021-09-04 19:06:21
 */
@Slf4j
@EnableBinding(StudyChannel.class)
public class KafkaMessageReceiveListener {

    /**
     * 接收默认消息
     * 
     * @param message 消息
     */
    @StreamListener(StudyChannel.DEFAULT_INPUT)
    public void receiveDefaultMsg(Message<String> message) {
        log.info("{}订阅默认消息：通道 = default，data = {}", DateUtil.now(), message);
    }

    /**
     * 接收告警消息
     * 
     * @param message 消息
     */
    @StreamListener(StudyChannel.ALERT_INPUT)
    public void receiveAlertMsg(Message<String> message) {
        log.info("{}订阅告警消息：通道 = alert，data = {}", DateUtil.now(), message);
    }
}
