package com.fank243.study.api;

import com.fank243.study.api.constants.ApiConstants;
import com.fank243.study.common.utils.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * MQ 消息发送器
 * 
 * @author FanWeiJie
 * @since 2021-09-04 16:44:19
 */
public interface IMessageApi {

    /**
     * 默认消息发送通道
     * 
     * @param message 消息
     */
    @GetMapping(ApiConstants.BASE_URI_MESSAGE + "/sendDefaultMsg")
    ResultInfo<?> sendDefaultMsg(@RequestBody String message);

    /**
     * 告警消息发送通道
     * 
     * @param message 消息
     */
    @GetMapping(ApiConstants.BASE_URI_MESSAGE + "/sendAlertMsg")
    ResultInfo<?> sendAlertMsg(@RequestBody String message);
}
