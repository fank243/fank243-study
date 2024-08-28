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

package com.github.fank243.kong.core.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 常量池
 * 
 * @author FanWeiJie
 * @since 2022-09-23 16:22:43
 */
public class Constants {

    /** 基包 **/
    public static final String BASE_PACKAGE = "com.github.fank243.kong";

    /** 日志 **/
    public static final String BASE_PACKAGE_LOG = BASE_PACKAGE + ".log";

    /** 短信 **/
    public static final String BASE_PACKAGE_SMS = BASE_PACKAGE + ".sms";

    public static final String BASE_PACKAGE_SUPPORT = BASE_PACKAGE_SMS + ".support";

    /** 基包 > service **/
    public static final String BASE_PACKAGE_SERVICE = "com.github.fank243.kong.**.service";

    /** 基包 > mapper **/
    public static final String BASE_PACKAGE_MAPPER = "com.github.fank243.kong.*.mapper";

    /** 跨服务调用安全令牌 Header Name **/
    public static final String SECURITY_TOKEN = "SecurityToken";

    /** 文件类型 **/
    public static final List<String> FILE_TYPE =
        Arrays.asList("txt", "xls", "xlsx", "png", "bmp", "gif", "jpg", "jpeg", "docx", "pdf", "ppt");

    /** 文件类型 > 文本文件 **/
    public static final List<String> FILE_TYPE_TEXT = List.of("txt");

    /** 文件类型 > 图片文件 **/
    public static final List<String> FILE_TYPE_IMAGE = Arrays.asList("png", "bmp", "gif", "jpg", "jpeg");

    /** 文件类型 > 办公类文件 **/
    public static final List<String> FILE_TYPE_OFFICE = Arrays.asList("xls", "xlsx", "docx", "pdf", "ppt");

    /** 文件URI前缀 > 仅被允许的 **/
    public static final List<String> FILE_PREFIX_ALL = Arrays.asList("static", "user");

    /** 文件URI前缀 > 免登录 **/
    public static final List<String> FILE_PREFIX_NOT_LOGIN = List.of("static");

    /** 文件URI前缀 > 登录用户 **/
    public static final String FILE_PREFIX_USER = "user";

    /** 文件大小(MB) **/
    public static final int FILE_SIZE = 10;

    /** 文件大小 > 图片文件大小(MB) **/
    public static final int FILE_SIZE_IMG = 2;

    /** 文件大小 > 办公类文件大小(MB) **/
    public static final int FILE_SIZE_OFFICE = 10;

    public static final String PUNCTUATION_POINT = ".";
}
