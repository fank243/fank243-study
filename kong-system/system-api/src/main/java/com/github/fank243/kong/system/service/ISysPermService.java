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

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.fank243.kong.core.constants.ServerConstants;
import com.github.fank243.kong.system.domain.vo.SysPermVO;

/**
 * 系统权限表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSysPermService", value = ServerConstants.SERVER_SYSTEM,
    path = ServerConstants.BASE_URI_SYSTEM + ServerConstants.BASE_URI_SYSTEM_PERM)
public interface ISysPermService {

    /**
     * 根据用户ID获取用户所有权限
     *
     * @param userId 用户ID
     * @return 用户权限
     */
    @GetMapping("/user/{userId}")
    List<SysPermVO> getByUserId(@PathVariable("userId") String userId);

    /**
     * 根据权限类型获取所有权限
     *
     * @param perms 权限类型列表
     * @return 权限列表
     */
    @PostMapping("/types")
    List<SysPermVO> getByPermTypes(@RequestBody List<Integer> perms);

}
