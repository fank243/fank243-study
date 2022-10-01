package com.fank243.study.file.web.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fank243.study.common.core.annotation.Interceptor;
import com.fank243.study.common.core.constants.InterceptorOrderConstant;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.common.core.utils.ServletUtils;

import cn.hutool.core.util.StrUtil;

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
        String domain = ServletUtils.getDomain(request);
        if (StrUtil.isBlank(referer) || !referer.startsWith(domain)) {
            ServletUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }
        // TODO FWJ 22/09/29 黑白名单拦截

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
