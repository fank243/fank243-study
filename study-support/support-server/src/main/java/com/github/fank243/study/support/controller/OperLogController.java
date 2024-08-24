/*
 * Copyright (c) 2024 fank243
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

package com.github.fank243.study.support.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.study.core.base.BaseController;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.core.domain.dto.OperLogDTO;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.log.OperLogEntity;
import com.github.fank243.study.support.domain.vo.OperLogVO;
import com.github.fank243.study.support.service.OperLogService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

/**
 * 请求响应日志表 控制器
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Tag(name = "OperLogController", description = "操作日志接口")
@RequestMapping(ServerConstants.BASE_URI_SUPPORT_LOG)
@RestController
public class OperLogController extends BaseController {

    @Resource
    private OperLogService operLogService;

    /**
     * 操作日志 > 根据日志ID获取
     * 
     * @param id 日志ID
     * @return 日志列表
     */
    @Operation(summary = "操作日志-根据ID获取", description = "获取操作记录")
    @Parameter(description = "操作日志记录ID")
    @GetMapping("/{id}")
    public ResultInfo<OperLogVO> getById(@PathVariable String id) {
        OperLogEntity operLogEntity = operLogService.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(operLogEntity, OperLogVO.class));
    }

    /**
     * 操作日志 > 分页
     *
     * @param operLogDTO 分页参数
     * @return 日志列表
     */
    @Operation(summary = "操作日志-分页")
    @PostMapping("/page")
    public ResultInfo<PageBean<OperLogVO>> page(@RequestBody OperLogDTO operLogDTO) {
        return ResultInfo.ok(operLogService.page(operLogDTO));
    }
}
