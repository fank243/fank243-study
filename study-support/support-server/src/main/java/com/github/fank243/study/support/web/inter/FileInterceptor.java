package com.github.fank243.study.support.web.inter;

import org.jetbrains.annotations.NotNull;

import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.annotation.Interceptor;
import com.github.fank243.study.core.constants.InterceptorOrderConstant;
import com.github.fank243.study.core.utils.WebUtils;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 文件访问拦截器
 * 
 * @author FanWeiJie
 * @since 2022-06-10 10:10:49
 */
@Interceptor(value = "fileInterceptor", include = {"/view/**"}, order = InterceptorOrderConstant.FILE)
public class FileInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull Object handler) throws Exception {
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
