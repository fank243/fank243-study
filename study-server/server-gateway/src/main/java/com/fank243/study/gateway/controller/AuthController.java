package com.fank243.study.gateway.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.exception.AuthException;
import com.fank243.study.gateway.domain.LoginUserDTO;
import com.fank243.study.gateway.domain.LoginUserVO;
import com.fank243.study.gateway.service.AuthService;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证
 *
 * @author FanWeiJie
 * @since 2021-11-24 14:05:21
 */
@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    /** 登录 **/
    @PostMapping("/login")
    public ResultInfo<?> login(@RequestBody LoginUserDTO loginDTO) throws AuthException {
        LoginUserVO loginUserVO = authService.login(loginDTO);
        return ResultInfo.ok(loginUserVO).message("登录成功");
    }

    /** 登出 **/
    @GetMapping("/logout")
    public ResultInfo<?> logout() {
        StpUtil.logout();
        return ResultInfo.ok().message("登出成功");
    }
}
