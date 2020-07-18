package com.fank243.springboot.app.shiro;

import com.fank243.springboot.app.model.AppRequest;
import com.fank243.springboot.app.utils.WebUtils;
import com.fank243.springboot.common.utils.JsonUtils;
import com.fank243.springboot.common.utils.ResultCode;
import com.fank243.springboot.common.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Restful Api 认证、鉴权
 *
 * @author FanWeiJie
 * @date 2020-03-31 21:26:57
 */
@Slf4j
public class MyShiroFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest)request;
        // 转换请求参数
        AppRequest appRequest = JsonUtils.cast(request.getParameterMap(), AppRequest.class);
        if (appRequest == null) {
            WebUtils.printJson(ResultInfo.fail(ResultCode.R104.getCode(), ResultCode.R104.getMsg()));
            return false;
        }
        MyShiroToken shiroToken = new MyShiroToken(appRequest);
        // 登录
        try {
            getSubject(request, response).login(shiroToken);
        } catch (AuthenticationException e) {
            log.error(e.toString(), e);
            WebUtils.printJson(ResultInfo.fail(e.getMessage()));
            return false;
        }
        return true;
    }
}
