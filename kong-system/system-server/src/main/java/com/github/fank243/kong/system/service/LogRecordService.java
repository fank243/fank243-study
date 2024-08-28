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
import java.util.stream.Collectors;

import com.github.fank243.kong.system.domain.vo.SysUserVO;

import cn.hutool.extra.spring.SpringUtil;

/**
 * 自定义处理函数
 * 
 * @author FanWeiJie
 * @since 2022-10-07 19:46:17
 */
public class LogRecordService {

    private final static SysUserService sysUserService = SpringUtil.getBean(SysUserService.class);

    public static String findUsername(String[] ids) {
        List<SysUserVO> sysUserList = sysUserService.findAllByUserIdIn(List.of(ids));
        return sysUserList.stream().map(SysUserVO::getUsername).collect(Collectors.joining("、"));
    }
}
