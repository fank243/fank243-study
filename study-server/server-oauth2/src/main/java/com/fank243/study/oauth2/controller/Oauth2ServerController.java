package com.fank243.study.oauth2.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.domain.enums.LoginType;
import com.fank243.study.system.domain.dto.SysUserLoginDTO;
import com.fank243.study.system.service.ISysUserLoginService;

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
    private ISysUserLoginService sysUserLoginFeignClient;

    /** 请求统一入口 **/
    @RequestMapping("/oauth2/*")
    public Object request() {
        SaResult saResult;
        try {
            saResult = (SaResult)SaOAuth2Handle.serverRequest();
        } catch (Exception e) {
            log.error("Oauth2认证异常：{}", e.getMessage(), e);
            return ResultInfo.error(e.getMessage(), e.toString());
        }
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
                SysUserLoginDTO loginReq = new SysUserLoginDTO();
                loginReq.setLoginType(LoginType.USERNAME.name());
                loginReq.setUsername(name);
                loginReq.setPassword(pwd);
                ResultInfo<String> result = sysUserLoginFeignClient.login(loginReq);
                if (!result.isSuccess()) {
                    return SaResult.error(result.getMessage()).setData(result);
                }
                userId = String.valueOf(result.getPayload());
            } catch (Exception e) {
                log.error("登录异常：{}", e.getMessage());
                return SaResult.error(e.getMessage()).setData(e);
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
