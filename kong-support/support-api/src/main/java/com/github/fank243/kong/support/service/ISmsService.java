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

package com.github.fank243.kong.support.service;

import com.github.fank243.kong.core.domain.dto.OperLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.constants.ServerConstants;

/**
 * 国家行政区划表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSmsService", value = ServerConstants.SERVER_SUPPORT,
    path = ServerConstants.BASE_URI_SUPPORT + ServerConstants.BASE_URI_SUPPORT_LOG)
public interface ISmsService {
    /**
     * 发送短信验证码
     *
     * @param operLogDTO 请求响应信息
     * @return 添加结果
     */
    @PostMapping("/send")
    ResultInfo<?> send(@RequestBody OperLogDTO operLogDTO);
}
