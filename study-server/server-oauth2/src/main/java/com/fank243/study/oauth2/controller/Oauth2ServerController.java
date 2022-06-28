package com.fank243.study.oauth2.controller;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.exception.AuthException;
import com.fank243.study.core.utils.ServletUtils;
import com.fank243.study.oauth2.service.OauthClientService;

import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.Header;
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
        SaResult saResult = (SaResult)SaOAuth2Handle.serverRequest();
        if (saResult.getCode() != SaResult.CODE_SUCCESS) {
            return ResultInfo.fail(saResult.getMsg());
        }
        return ResultInfo.ok(saResult.getMsg(), StpUtil.getTokenInfo());
    }

    /** 登出 **/
    @GetMapping("/oauth2/logout")
    public ResultInfo<?> logout() {
        StpUtil.logout();
        return ResultInfo.ok().message("登出成功");
    }

    @Autowired
    public void oauth2Config(SaOAuth2Config cfg) {
        cfg.setDoLoginHandle((name, pwd) -> {
            String userId;
            try {
                HttpServletRequest request = ServletUtils.getRequest();
                assert request != null;
                String clientIp = ServletUtil.getClientIP(request);
                String userAgent = ServletUtil.getHeader(request, Header.USER_AGENT.getValue(), StandardCharsets.UTF_8);
                userId = oauthClientService.login(name, pwd, clientIp, userAgent);
            } catch (AuthException e) {
                log.error(e.getMessage(), e);
                return SaResult.error(e.getMessage());
            }
            StpUtil.login(userId, "PC");
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
