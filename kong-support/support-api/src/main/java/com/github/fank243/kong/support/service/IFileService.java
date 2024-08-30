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

import org.springframework.cloud.openfeign.FeignClient;

import com.github.fank243.kong.core.constants.ServerConstants;

/**
 * 文件表 客户端
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@FeignClient(contextId = "iFileService", value = ServerConstants.SERVER_SUPPORT, path = ServerConstants.BASE_URI_FILE)
public interface IFileService {

}