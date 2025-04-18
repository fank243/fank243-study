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

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.github.fank243.kong.core.utils.WebUtils;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 请求日志拦截打印
 *
 * @author FanWeiJie
 * @since 2023-05-31 09:58
 */
@Component
@Aspect
public class LogAspect {

    @Around("execution(public * com.github.fank243.kong.*.controller..*(..))")
    public Object round(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HttpServletRequest request = WebUtils.getRequest();
        if (request == null) {
            return point.proceed();
        }
        ReqRespLog reqRespLog = new ReqRespLog();
		if (StpUtil.isLogin()) {
			reqRespLog.setUserId(StpUtil.getLoginIdAsString());
		}
        reqRespLog.setReqUri(request.getRequestURI());
        reqRespLog.setReqMethod(request.getMethod());
        reqRespLog.setReqHeader(JSONUtil.toJsonStr(WebUtils.getHeader(request)));
        reqRespLog.setContentType(request.getContentType());
        reqRespLog.setReqBody(JSONUtil.toJsonStr(args));
        reqRespLog.setReqTime(new Date());
        reqRespLog.setClientIp(JakartaServletUtil.getClientIP(request));

        Object result = point.proceed(args);
        if (result != null) {
            reqRespLog.setRespBody(JSONUtil.toJsonStr(result));
        }
        reqRespLog.setRespTime(new Date());

        LogUtils.printLog(reqRespLog);

        return result;
    }
}
