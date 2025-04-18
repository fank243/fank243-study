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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.constants.DateConstants;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "短信请求实体")
public class SmsDTO extends BaseDTO {

    @Schema(description = "短信ID")
    private String smsId;

    /*** 消息发送ID */
    @Schema(description = "消息发送ID")
    private String msgId;

    /*** 手机号码 */
    @Schema(description = "手机号码")
    private String mobile;

    /** 短信内容 */
    @Schema(description = "短信内容")
    private String content;

    /*** 创建时间 */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateConstants.YY_MM_DD_HH_MM_SS)
    private String createdDate;

}
