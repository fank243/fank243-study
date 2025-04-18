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

package com.github.fank243.kong.gateway.constants;

import java.io.Serializable;

import org.springframework.data.repository.query.parser.Part;
import org.springframework.util.MultiValueMap;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * 上下文对象
 * 
 * @author FanWeiJie
 * @since 2022-03-11 14:57:36
 */
@Data
public class GatewayContext implements Serializable {

    public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";

    /**
     * cache json body
     */
    private String cacheBody;
    /**
     * cache form data
     */
    private MultiValueMap<String, Part> formData;
    /**
     * cache request path
     */
    private String path;

    public String getCacheBody() {
        return StrUtil.isNotBlank(this.cacheBody) ? JSONUtil.toJsonStr(JSONUtil.parseObj(this.cacheBody)) : "{}";
    }
}
