/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.support.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fank243.kong.core.annotation.RepeatSubmit;
import com.github.fank243.kong.core.base.BaseController;
import com.github.fank243.kong.core.constants.ServerConstants;
import com.github.fank243.kong.core.exception.BizException;
import com.github.fank243.kong.support.domain.dto.SmsCodeDTO;
import com.github.fank243.kong.support.domain.dto.SmsContentDTO;
import com.github.fank243.kong.support.service.SmsService;
import com.github.fank243.kong.tool.result.ResultInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Tag(name = "SmsController", description = "短信接口")
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
    @Operation(summary = "短信-发送")
    @RepeatSubmit
    @PostMapping("/send")
    public ResultInfo<?> send(@RequestBody @Validated SmsContentDTO smsContentDTO) throws BizException {
        return smsService.send(smsContentDTO);
    }

    /**
     * 短信 > 发送验证码
     *
     * @param smsCodeDTO 请求参数
     * @return 操作结果
     */
    @Operation(summary = "短信-发送验证码")
    @RepeatSubmit
    @PostMapping("/send/code")
    public ResultInfo<?> sendSmsCode(@RequestBody @Validated(SmsCodeDTO.Send.class) SmsCodeDTO smsCodeDTO)
        throws BizException {
        return smsService.sendSmsCode(smsCodeDTO);
    }

    /**
     * 短信 > 校验
     *
     * @param smsCodeDTO 请求参数
     * @return 操作结果
     */
    @Operation(summary = "短信-校验短信验证码")
    @PostMapping("/validate")
    public ResultInfo<?> validateSmsCode(@RequestBody @Validated(SmsCodeDTO.Validate.class) SmsCodeDTO smsCodeDTO) {
        return smsService.validateSmsCode(smsCodeDTO);
    }

}
