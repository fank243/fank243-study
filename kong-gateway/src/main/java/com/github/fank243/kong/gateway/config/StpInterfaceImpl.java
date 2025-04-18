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

package com.github.fank243.kong.gateway.config;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.fank243.kong.system.domain.vo.SysPermVO;
import com.github.fank243.kong.system.domain.vo.SysRoleVO;
import com.github.fank243.kong.system.service.ISysPermService;
import com.github.fank243.kong.system.service.ISysRoleService;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import jakarta.annotation.Resource;

/**
 * saToken注册统一鉴权
 *
 * @author FanWeiJie
 * @since 2022-05-11 10:46:17
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysPermService sysPermService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        Future<List<SysPermVO>> future = ThreadUtil.execAsync(() -> sysPermService.getByUserId((String)loginId));
        List<SysPermVO> perms;
        try {
            perms = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (CollUtil.isEmpty(perms)) {
            return null;
        }
        return perms.stream().map(SysPermVO::getPermCode).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        Future<List<SysRoleVO>> future = ThreadUtil.execAsync(() -> sysRoleService.getByUserId((String)loginId));
        List<SysRoleVO> roles;
        try {
            roles = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (CollUtil.isEmpty(roles)) {
            return null;
        }
        return roles.stream().map(SysRoleVO::getRoleCode).collect(Collectors.toList());
    }

}
