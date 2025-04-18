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
import com.github.fank243.kong.core.domain.model.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.constants.ServerConstants;
import com.github.fank243.kong.support.domain.vo.OperLogVO;

/**
 * 操作日志 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iLogService", value = ServerConstants.SERVER_SUPPORT,
    path = ServerConstants.BASE_URI_SUPPORT + ServerConstants.BASE_URI_SUPPORT_LOG)
public interface IOperLogService {

    /**
     * 操作日志 > 根据日志ID获取
     *
     * @param id 日志ID
     * @return 日志列表
     */
    @GetMapping("/{id}")
    ResultInfo<OperLogVO> getById(@PathVariable String id);

    /**
     * 操作日志 > 分页
     *
     * @param operLogDTO 分页参数
     * @return 日志列表
     */
    @PostMapping("/page")
    ResultInfo<PageBean<OperLogVO>> page(@RequestBody OperLogDTO operLogDTO);

}
