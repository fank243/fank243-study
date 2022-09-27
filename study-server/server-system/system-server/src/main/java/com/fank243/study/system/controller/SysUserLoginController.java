package com.fank243.study.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.Header;
import com.fank243.study.common.utils.ServletUtils;
import com.fank243.study.common.web.exception.AuthException;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.system.api.ISysUserLoginApi;
import com.fank243.study.api.system.domain.dto.SysUserLoginDTO;
import com.fank243.study.common.domain.base.BaseController;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.common.utils.ValidationUtils;
import com.fank243.study.core.constants.ValidatorGroup;
import com.fank243.study.core.domain.enums.LoginType;
import com.fank243.study.system.service.SysUserService;

import cn.hutool.core.util.EnumUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * 管理员登录 控制器
 * 
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Slf4j
@RestController
public class SysUserLoginController extends BaseController implements ISysUserLoginApi {

    @Resource
    private SysUserService sysUserService;

    @Override
    public ResultInfo<String> login(SysUserLoginDTO loginReq) throws AuthException {
        Class<?> clazz = ValidatorGroup.LoginMobile.class;
        if (EnumUtil.equalsIgnoreCase(LoginType.USERNAME, loginReq.getLoginType())) {
            clazz = ValidatorGroup.LoginUsername.class;
        }
        ResultInfo<?> result = ValidationUtils.validate(loginReq, clazz);
        if (!result.isSuccess()) {
            throw new IllegalArgumentException(result.getMessage());
        }
        HttpServletRequest request = ServletUtils.getRequest();
        assert request != null;
        String clientIp = ServletUtil.getClientIP(request);
        String userAgent = ServletUtil.getHeader(request, Header.USER_AGENT.getValue(), StandardCharsets.UTF_8);

        String userId = sysUserService.login(loginReq, clientIp, userAgent);

        return ResultInfo.ok(userId);
    }
}
