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

package com.github.fank243.kong.core.model.log;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 请求响应日志表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
public class ReqRespLog implements Serializable {

    /*** 日志类型 */
    private String logType;

    /** 操作人ID */
    private String userId;

    /** 客户端ID */
    private String clientIp;

    /** 请求方式 */
    private String reqMethod;

    /** 请求地址 */
    private String reqUri;

    /** 媒体类型 */
    private String contentType;

    /** 请求头参数 */
    private String reqHeader;

    /** 请求参数 */
    private String reqBody;

    /** 响应参数 */
    private String respBody;

    /** 请求时间 */
    private Date reqTime;

    /** 响应时间 */
    private Date respTime;

    /** 执行时间 */
    private Long executeTime;

    /** traceId */
    private String traceId;

    /** spanId */
    private String spanId;

    public Long getExecuteTime() {
        return this.respTime.getTime() - this.reqTime.getTime();
    }
}
