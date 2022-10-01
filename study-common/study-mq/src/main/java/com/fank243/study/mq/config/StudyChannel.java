package com.fank243.study.mq.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 定义消息通道
 *
 * @author FanWeiJie
 * @since 2021-09-04 15:41:41
 */
public interface StudyChannel {

    /** 默认发送消息通道名称 **/
    String DEFAULT_OUTPUT = "study_default_output";
    /** 默认接收消息通道名称 **/
    String DEFAULT_INPUT = "study_default_input";

    /** 告警发送消息通道名称 **/
    String ALERT_OUTPUT = "study_alert_output";
    /** 告警接收消息通道名称 **/
    String ALERT_INPUT = "study_alert_input";

    /**
     * 默认发送消息通道
     *
     * @return 默认发送消息通道
     */
    @Output(DEFAULT_OUTPUT)
    MessageChannel sendDefaultMsg();

    /**
     * 告警发送消息通道
     *
     * @return 告警发送消息通道
     */
    @Output(ALERT_OUTPUT)
    MessageChannel sendAlertMsg();

    /**
     * 默认接收消息通道
     *
     * @return 默认接收消息通道
     */
    @Input(DEFAULT_INPUT)
    SubscribableChannel receiveDefaultMsg();

    /**
     * 告警接收消息通道
     *
     * @return 告警接收消息通道
     */
    @Input(ALERT_INPUT)
    SubscribableChannel receiveAlertMsg();
}