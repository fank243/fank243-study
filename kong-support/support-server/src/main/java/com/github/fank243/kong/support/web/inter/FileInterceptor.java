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

package com.github.fank243.kong.support.web.inter;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.annotation.Interceptor;
import com.github.fank243.kong.core.constants.InterceptorOrderConstant;
import com.github.fank243.kong.core.constants.enums.EnvEnum;
import com.github.fank243.kong.core.utils.WebUtils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 文件访问拦截器
 *
 * @author FanWeiJie
 * @since 2022-06-10 10:10:49
 */
@Interceptor(value = "fileInterceptor", include = {"/file/**"}, order = InterceptorOrderConstant.FILE)
public class FileInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull Object handler) throws Exception {
        if (!EnvEnum.PROD.name().equals(SpringUtil.getActiveProfile())) {
            return Boolean.TRUE;
        }
        // referer 拦截
        String referer = request.getHeader("referer");
        String domain = WebUtils.getDomain(request);
        if (StrUtil.isBlank(referer) || !referer.startsWith(domain)) {
            WebUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }
        // TODO FWJ 22/09/29 黑白名单拦截

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
