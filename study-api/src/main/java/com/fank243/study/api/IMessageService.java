package com.fank243.study.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * MQ 消息发送器
 * 
 * @author FanWeiJie
 * @since 2021-09-04 16:44:19
 */
@FeignClient(value = "server-system")
public interface IMessageService {}
