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

package com.github.fank243.kong.core.base;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base Controller
 *
 * @author FanWeiJie
 * @since 1.0.0
 */
@RestController
public class BaseController {

	/**
	 * 获取登录用户ID，如果用户未登录则返回空串
	 *
	 * @return 用户ID
	 */
	protected String getLoginId() {
		return StpUtil.isLogin() ? StpUtil.getLoginIdAsString() : "";
	}

	/**
	 * 退出登陆
	 */
	protected void logout() {
		StpUtil.logout();
	}
}
