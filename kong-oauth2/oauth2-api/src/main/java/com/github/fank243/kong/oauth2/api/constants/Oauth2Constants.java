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

package com.github.fank243.kong.oauth2.api.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Oauth2 常量池
 * 
 * @author FanWeiJie
 * @since 2022-10-03 03:16:50
 */
public class Oauth2Constants {

    /** 用户重复 **/
    public static final int USER_REPEAT_CODE = 2002;
    public final static String LOGIN = "login";
    public final static String CONFIRM = "confirm";
    public final static String SYSTEM = "/system";

    /** 授权类型 **/
    public enum GrantType {
        /** 密码模式 **/
        PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, CLIENT_CREDENTIALS, IMPLICIT
    }

    /** 权限范围 **/
    public enum Scope {
        /** 用户基本信息 **/
        USER_INFO,

        /** 添加用户 **/
        USER_MODIFY,

        ;

        public static final List<String> SCOPE_ALL =
            Arrays.asList(USER_INFO.name().toLowerCase(), USER_MODIFY.name().toLowerCase());
    }
}
