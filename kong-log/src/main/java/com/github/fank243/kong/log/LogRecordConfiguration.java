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

package com.github.fank243.kong.log;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import com.mzt.logapi.starter.annotation.EnableLogRecord;

import cn.dev33.satoken.stp.StpUtil;

/**
 * 操作日志配置
 *
 * @author FanWeiJie
 * @since 2022-10-07 21:00:04
 */
@EnableLogRecord(tenant = "com.github.fank243.kong")
@Configuration
public class LogRecordConfiguration {

    @Bean
    public IOperatorGetService operatorGetService() {
        return () -> Optional.of(StpUtil.getTokenInfo()).map(a -> new Operator((String)a.getLoginId()))
            .orElseThrow(() -> new IllegalArgumentException("user is null"));
    }
}
