package com.fank243.springboot.admin.interceptor;

import com.fank243.springboot.admin.model.ActiveUser;
import com.fank243.springboot.admin.service.component.RedisService;
import com.fank243.springboot.admin.utils.ThreadUtils;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.annotation.Interceptor;
import com.fank243.springboot.core.consts.RedisKey;
import com.fank243.springboot.core.consts.SysKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台拦截器
 *
 * @author FanWeiJie
 * @date 2019-10-26 12:04:12
 */
@Slf4j
@Interceptor(value = "adminInterceptor", include = "/admin/**", order = 0)
public class AdminInterceptor implements HandlerInterceptor {

    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = StrUtils.getUUID();
        ThreadUtils.set(requestId);
        if (log.isDebugEnabled()) {
            MDC.put("sessionId", request.getRequestedSessionId());
            MDC.put("requestId", requestId);
            log.debug("{} [{}] body[{}] header[{}]", request.getRequestURI(), WebUtils.getIp(request),
                WebUtils.getBody(request), WebUtils.getHeader(request));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) {

        // 用户信息
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
        request.setAttribute("activeUser", activeUser);
        request.setAttribute("menuList", activeUser != null ? activeUser.getMenus() : "");

        // 站点信息
        String siteName = redisService.hget(RedisKey.SYS_CONFIG, SysKey.SITE_NAME) + "";
        request.setAttribute("siteName", siteName);
    }
}
