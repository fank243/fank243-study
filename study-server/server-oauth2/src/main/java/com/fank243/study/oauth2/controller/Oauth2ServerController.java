package com.fank243.study.oauth2.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.core.exception.AuthException;
import com.fank243.study.oauth2.service.OauthClientService;

import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Sa-OAuth2 Server端 控制器
 * 
 * @author FanWeiJie
 * @since 2021-11-24 16:26:32
 */
@Slf4j
@RestController
public class Oauth2ServerController {

    @Resource
    private OauthClientService oauthClientService;

    /** 请求统一入口 **/
    @RequestMapping("/oauth2/*")
    public Object request() {
        return SaOAuth2Handle.serverRequest();
    }

    @Autowired
    public void oauth2Config(SaOAuth2Config cfg) {
        cfg.setDoLoginHandle((name, pwd) -> {
            String userId;
            try {
                userId = oauthClientService.login(name, pwd);
            } catch (AuthException e) {
                e.printStackTrace();
                return SaResult.error(e.getMessage());
            }
            StpUtil.login(userId);
            return SaResult.ok();
        });
        // 开启密码认证模式
        cfg.setIsPassword(true);
    }

    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }

}
