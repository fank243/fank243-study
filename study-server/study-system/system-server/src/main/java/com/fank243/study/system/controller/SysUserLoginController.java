package com.fank243.study.system.controller;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.json.JSONUtil;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.common.core.base.BaseController;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.common.core.utils.ServletUtils;
import com.fank243.study.common.core.utils.ValidationUtils;
import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.common.core.constants.ValidatorGroup;
import com.fank243.study.common.core.domain.enums.LoginType;
import com.fank243.study.system.constants.SystemApiConstants;
import com.fank243.study.system.domain.dto.SysUserLoginDTO;
import com.fank243.study.system.service.SysUserService;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.Header;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理员登录 控制器
 * 
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Slf4j
@RequestMapping(SystemApiConstants.BASE_URI_SYSTEM)
@RestController
public class SysUserLoginController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 管理员登录
     * 
     * @param loginReq 请求参数
     * @return 登录结果
     * @throws AuthException 登录认证异常时抛出此异常
     */
    @PostMapping("/login")
    public ResultInfo<String> login(@RequestBody @Validated(ValidatorGroup.Login.class) SysUserLoginDTO loginReq)
        throws AuthException {
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
