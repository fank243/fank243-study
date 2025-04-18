/*
 * Copyright (c) 2024 fank243
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

package com.github.fank243.study.core.model.log;

import java.util.Date;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.study.core.utils.WebUtils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Log Tool
 * 
 * @author FanWeiJie
 * @since 2020-10-25 15:44:35
 */
@Slf4j
public class LogUtils {

    /**
     * 打印日志
     * 
     * @param request HttpServletRequest
     * @param result 响应内容
     */
    public static void printLog(String desc, HttpServletRequest request, ResultInfo<?> result) {
        printLog(desc, request, JSONUtil.toJsonStr(result));
    }

    /**
     * 打印日志
     * 
     * @param request HttpServletRequest
     * @param respBody 响应内容
     */
    public static void printLog(String desc, HttpServletRequest request, String respBody) {
        ReqRespLog reqRespLog = new ReqRespLog();
        reqRespLog.setReqMethod(request.getMethod());
        reqRespLog.setReqUri(request.getRequestURI());
        reqRespLog.setReqHeader(JSONUtil.toJsonStr(WebUtils.getHeader(request)));
        reqRespLog.setReqBody(JSONUtil.toJsonStr(WebUtils.getParams(request)));
        reqRespLog.setReqTime(new Date());
        reqRespLog.setRespBody(respBody);
        reqRespLog.setRespTime(new Date());

        printLog(desc, reqRespLog);
    }

    /**
     * 打印日志
     *
     * @param reqRespLog 请求参数
     */
    public static void printLog(ReqRespLog reqRespLog) {
        printLog("", reqRespLog);
    }

    /**
     * 打印日志
     *
     * @param reqRespLog 请求参数
     */
    public static void printLog(String desc, ReqRespLog reqRespLog) {
        if (log.isDebugEnabled()) {
            if (StrUtil.isNotBlank(desc)) {
                log.debug("请求日志[{}]：{} {} {}", desc, reqRespLog.getReqMethod(), reqRespLog.getReqUri(),
                    JSONUtil.toJsonStr(reqRespLog));
            } else {
                log.debug("请求日志：{} {} {}", reqRespLog.getReqMethod(), reqRespLog.getReqUri(),
                    JSONUtil.toJsonStr(reqRespLog));
            }
        }
    }

}
