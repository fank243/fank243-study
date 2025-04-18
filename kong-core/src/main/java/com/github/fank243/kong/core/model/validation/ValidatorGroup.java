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

package com.github.fank243.kong.core.model.validation;

import jakarta.validation.groups.Default;

/**
 * Hibernate Validator
 * 
 * @author FanWeiJie
 * @since 2021-08-28 18:50:00
 */
public interface ValidatorGroup {

    /** 新增 **/
    interface Create extends Default {}

    /** 修改 **/
    interface Modify extends Default {}

    /** 登录 **/
    interface Login extends Default {}

    /** 登录 > 手机号码 **/
    interface LoginMobile extends Login {}

    /** 登录 > 用户名 **/
    interface LoginUsername extends Login {}
}
