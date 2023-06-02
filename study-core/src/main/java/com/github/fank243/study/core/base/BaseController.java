package com.github.fank243.study.core.base;

import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Base Controller
 * 
 * @author FanWeiJie
 * @since 2021-06-08 22:41:55
 * @ignore
 */
@RestController
public class BaseController {

    /**
     * 获取登录用户ID，如果用户未登录则返回空串
     * 
     * @return 用户ID
     */
    protected String getLoginId() {
        return StpUtil.isLogin() ? StpUtil.getLoginIdAsString() : "";
    }

    /**
     * 退出登陆
     */
    protected void logout() {
        StpUtil.logout();
    }
}
