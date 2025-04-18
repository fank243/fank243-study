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

package com.github.fank243.kong.core.constants;

/**
 * Cache Key
 * 
 * @author FanWeiJie
 * @since 2022-09-26 14:03:40
 */
public class CacheConstants {
    /** 跨服务调用安全令牌 **/
    public static final String SECURITY_TOKEN = "security:token:";

    /** Oauth2 授权令牌 **/
    public static final String OAUTH2_TOKEN = "studyToken:login:oauth2:";

    /** 图形验证码 **/
    public static final String IMG_CODE_KEY = "security:code:image:";

    /** 短信验证码 **/
    public static final String SMS_CODE_KEY = "security:code:sms:";

    /** 手机号码发送短信锁定 **/
    public static final String SMS_MOBILE_LOCK = "security:mobile:";

    /** 文件ID KEY **/
    public static final String FILE_ID_KEY = "files:file";

}
