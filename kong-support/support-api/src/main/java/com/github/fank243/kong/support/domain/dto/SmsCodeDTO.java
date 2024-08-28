/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.support.domain.dto;

import com.github.fank243.kong.tool.pattern.RegexExtPool;
import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.model.validation.annotation.Enum;
import com.github.fank243.kong.support.constants.SmsCodeTypeEnum;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 短信验证码
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Schema(description = "短信验证码请求实体")
public class SmsCodeDTO extends BaseDTO {

    /*** 手机号码 */
    @Pattern(regexp = RegexPool.MOBILE, message = "请填写正确的手机号码", groups = {Send.class, Validate.class})
    @NotBlank(message = "请填写手机号码", groups = {Send.class, Validate.class})
    @Schema(description = "手机号码")
    private String mobile;

    /*** 验证码类型 */
    @Enum(clazz = SmsCodeTypeEnum.class, message = "验证码类型必传", groups = {Send.class})
    @Schema(description = "验证码类型")
    private Integer smsCodeType;

    /*** 消息发送ID */
    @NotBlank(message = "消息发送ID必传", groups = {Validate.class})
    @Schema(description = "消息发送ID")
    private String msgId;

    /*** 短信验证码 */
    @Pattern(regexp = RegexExtPool.NUMBER_SIX, message = "请填写短信验证码", groups = {Validate.class})
    @NotBlank(message = "请填写短信验证码", groups = {Validate.class})
    @Schema(description = "短信验证码")
    private String smsCode;

    public interface Send {}

    public interface Validate {}
}
