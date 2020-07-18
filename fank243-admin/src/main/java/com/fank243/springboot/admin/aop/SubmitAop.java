package com.fank243.springboot.admin.aop;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.Submit;
import com.fank243.springboot.core.consts.IConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 防止表单提交过于频繁
 *
 * @author FanWeiJie
 * @date 2020-04-11 21:36:10
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class SubmitAop {

    @CreateCache(name = "submitCache", expire = 5, cacheType = CacheType.LOCAL)
    private Cache<String, Object> submitCache;

    @Pointcut("@annotation(com.fank243.springboot.core.annotation.Submit)")
    public void point() {}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = WebUtils.getRequest();
        String cacheKey = request.getRequestURI() + "-" + request.getRequestedSessionId();

        String msg = "操作过于频繁，喝口水再来哈";
        String redirectUri = "/error/msg?message=" + URLEncoder.encode(msg, StandardCharsets.UTF_8.name());

        Object value = submitCache.get(cacheKey);
        if (value != null) {
            if (WebUtils.isAjax(request)) {
                return ResultInfo.fail(msg);
            }
            WebUtils.getResponse().sendRedirect(redirectUri);
            return null;
        }
        submitCache.put(cacheKey, joinPoint.getArgs()[0]);

        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Submit annotation = method.getAnnotation(Submit.class);

        // 防止表单重复提交，支持ajax
        if (annotation.repeat()) {
            HttpSession session = request.getSession();
            String ajaxToken = request.getParameter(IConsts.AUTHENTICITY_TOKEN);
            if (StringUtils.isBlank(ajaxToken)) {
                return ResultInfo.fail("页面失效，请刷新页面重试");
            }
            String sessionAjaxToken = session.getAttribute(IConsts.AUTHENTICITY_TOKEN) + "";
            if (StringUtils.isBlank(sessionAjaxToken)) {
                return ResultInfo.fail("请勿重复提交表单");
            }
            if (!ajaxToken.equalsIgnoreCase(sessionAjaxToken)) {
                return ResultInfo.fail("页面已过期，请刷新页面重试");
            }

            // 获取响应结果
            Object proceed = joinPoint.proceed();
            if (proceed instanceof ResultInfo) {
                ResultInfo result = (ResultInfo)proceed;
                // 如果业务处理成功，则移除
                if (result.isSuccess()) {
                    session.removeAttribute(IConsts.AUTHENTICITY_TOKEN);
                }
            }
            return proceed;
        }

        return joinPoint.proceed();
    }
}
