package com.fank243.study.stream.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.IMessageApi;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.base.BaseController;
import com.fank243.study.mq.message.KafkaMessageSender;

/**
 * <p>
 * 系统管理员表 控制器
 * </p>
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@RestController
public class MessageController extends BaseController implements IMessageApi {

    @Resource
    private KafkaMessageSender kafkaMessageSender;

    @Override
    public ResultInfo<?> sendDefaultMsg(String message) {
        kafkaMessageSender.sendToDefaultChannel(message);
        return ResultInfo.ok();
    }

    @Override
    public ResultInfo<?> sendAlertMsg(String message) {
        kafkaMessageSender.sendToAlertChannel(message);
        return ResultInfo.ok();
    }
}
