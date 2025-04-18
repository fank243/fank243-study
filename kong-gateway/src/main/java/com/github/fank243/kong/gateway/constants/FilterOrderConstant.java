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

package com.github.fank243.kong.gateway.constants;

import com.github.fank243.kong.gateway.web.filter.ApiLogFilter;
import com.github.fank243.kong.gateway.web.filter.SecurityFilter;
import com.github.fank243.kong.gateway.web.filter.ValidateImageCodeFilter;

/**
 * 拦截器拦截顺序
 *
 * @author FanWeiJie
 * @since 2022-03-11 15:42:41
 */
public class FilterOrderConstant {

    /**
     * 获取特定拦截器序号
     *
     * @param clsName 拦截器全限定类名
     * @return 序号
     */
    public static int getOrder(String clsName) {
        // 安全令牌拦截器
        if (SecurityFilter.class.getName().equalsIgnoreCase(clsName)) {
            return -100;
        }
        // Skywalking 链路追踪ID拦截处理器
        // if (TraceIdFilter.class.getName().equalsIgnoreCase(clsName)) {
        // return -99;
        // }
        // 请求参数拦截处理器
        else if (ApiLogFilter.class.getName().equalsIgnoreCase(clsName)) {
            return -98;
        }
        // 请求参数拦截处理器
        else if (ValidateImageCodeFilter.class.getName().equalsIgnoreCase(clsName)) {
            return -97;
        }
        return 0;
    }
}
