package com.fank243.study.support.controller;

import javax.annotation.Resource;

import com.fank243.study.support.service.SmsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.common.core.annotation.RepeatSubmit;
import com.fank243.study.common.core.base.BaseController;
import com.fank243.study.common.core.constants.ServerConstants;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.support.domain.dto.SmsCodeDTO;
import com.fank243.study.support.domain.dto.SmsContentDTO;
import com.fank243.study.support.service.ISmsService;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Slf4j
@RequestMapping(ServerConstants.BASE_URI_SUPPORT_SMS)
@RestController
public class SmsController extends BaseController {

    @Resource
    private SmsService smsService;

    /**
     * 短信 > 发送
     *
     * @param smsContentDTO 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping("/send")
    public ResultInfo<?> send(@RequestBody @Validated SmsContentDTO smsContentDTO) {
        return smsService.send(smsContentDTO);
    }

    /**
     * 短信 > 发送验证码
     * 
     * @param smsCodeDTO 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping("/sendSmsCode")
    public ResultInfo<?> sendSmsCode(@RequestBody @Validated(SmsCodeDTO.Send.class) SmsCodeDTO smsCodeDTO) {
        return smsService.sendSmsCode(smsCodeDTO);
    }

    /**
     * 短信 > 校验
     * 
     * @param smsCodeDTO 请求参数
     * @return 操作结果
     */
    @PostMapping("/validateSmsCode")
    public ResultInfo<?> validateSmsCode(@RequestBody @Validated(SmsCodeDTO.Validate.class) SmsCodeDTO smsCodeDTO) {
        return smsService.validateSmsCode(smsCodeDTO);
    }

}
