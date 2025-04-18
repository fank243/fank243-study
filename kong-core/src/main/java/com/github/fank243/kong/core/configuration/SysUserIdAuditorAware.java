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

package com.github.fank243.kong.core.configuration;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import cn.dev33.satoken.stp.StpUtil;

/**
 * JPA 审计获取当前登录用户
 * 
 * @author FanWeiJie
 * @since 1.2.3
 */
@Configuration
public class SysUserIdAuditorAware implements AuditorAware<Long> {
    @NotNull
    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            if (StpUtil.isLogin()) {
                return Optional.of(StpUtil.getLoginIdAsLong());
            }
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }
}