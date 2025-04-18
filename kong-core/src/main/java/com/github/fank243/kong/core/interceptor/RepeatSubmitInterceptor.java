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

package com.github.fank243.kong.core.interceptor;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

import com.github.fank243.kong.core.annotation.RepeatSubmit;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * 重复提交拦截器：基于请求参数
 * 
 * @author FanWeiJie
 * @since 2022-06-10 10:10:49
 */
@SuppressWarnings("unchecked")
@Component
@ConditionalOnWebApplication(type = SERVLET)
public class RepeatSubmitInterceptor extends AbstractRepeatSubmitInterceptor {

    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();
        String body = SecureUtil.md5(JSONUtil.toJsonStr(JakartaServletUtil.getParamMap(request)));

        Object obj = session.getAttribute("repeatSubmitParam");
        if (obj != null) {
            Map<String, Object> sessionMap = (Map<String, Object>)obj;
            if (sessionMap.containsKey(uri)) {
                Map<String, Object> dataMap = (Map<String, Object>)sessionMap.get(uri);
                String param = (String)dataMap.getOrDefault("param", "");
                long time = Convert.toLong(dataMap.get("time"), 0L);

                if (param.equalsIgnoreCase(body) && System.currentTimeMillis() - time < annotation.interval()) {
                    return Boolean.TRUE;
                }
            }
        }

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("param", body);
        dataMap.put("time", System.currentTimeMillis());

        Map<String, Object> sessionMap = new HashMap<>(1);
        sessionMap.put(uri, dataMap);

        session.setAttribute("repeatSubmitParam", sessionMap);
        return Boolean.FALSE;
    }
}
