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
 * 拦截器序号
 * 
 * @author FanWeiJie
 * @since 2022-06-27 14:32:17
 */
public class InterceptorOrderConstant {

    /** traceId **/
    public static final int TRACE_ID = 0;

    /** Security **/
    public static final int SECURITY = 1;

    /** 防重复提交 **/
    public static final int REPEAT_SUBMIT = 2;

    /** 文件访问 **/
    public static final int FILE = 3;
}
