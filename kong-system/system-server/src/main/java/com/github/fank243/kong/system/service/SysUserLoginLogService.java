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

package com.github.fank243.kong.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fank243.kong.system.domain.entity.SysUserLoginLogEntity;
import com.github.fank243.kong.system.repository.ISysUserLoginLogRepository;

import jakarta.annotation.Resource;

/**
 * 系统管理员登录日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Service
public class SysUserLoginLogService {

    @Resource
    private ISysUserLoginLogRepository sysUserLoginLogRepository;

    /**
     * 系统管理员登录日志表_新增
     *
     * @param sysUserLoginLog 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public SysUserLoginLogEntity add(SysUserLoginLogEntity sysUserLoginLog) {
        return sysUserLoginLogRepository.save(sysUserLoginLog);
    }

}
