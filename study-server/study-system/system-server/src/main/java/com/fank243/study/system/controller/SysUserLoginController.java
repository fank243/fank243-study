package com.fank243.study.system.controller;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.common.core.base.BaseController;
import com.fank243.study.common.core.constants.ServerConstants;
import com.fank243.study.common.core.constants.ValidatorGroup;
import com.fank243.study.common.core.domain.enums.LoginType;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.common.core.utils.ValidationUtils;
import com.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.fank243.study.oauth2.api.domain.vo.OauthAccessTokenVO;
import com.fank243.study.oauth2.api.domain.vo.OauthUserVO;
import com.fank243.study.oauth2.api.service.IOauth2Service;
import com.fank243.study.system.config.Oauth2Properties;
import com.fank243.study.system.domain.dto.SysUserLoginDTO;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.domain.vo.SysUserLoginVO;
import com.fank243.study.system.service.SysUserService;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理员登录 控制器
 * 
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Slf4j
@RequestMapping(ServerConstants.BASE_URI_SYSTEM)
@RestController
public class SysUserLoginController extends BaseController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private IOauth2Service oauth2Service;
    @Resource
    private Oauth2Properties oauth2Properties;

    /**
     * Oauth2 > 密码模式
     *
     * @param sysUserLoginDTO 请求参数
     * @return 登录用户信息
     */
    @PostMapping("/login")
    public ResultInfo<?> login(HttpServletRequest request,
        @RequestBody @Validated({ValidatorGroup.Login.class}) SysUserLoginDTO sysUserLoginDTO) {
        Class<?> clazz = ValidatorGroup.LoginUsername.class;
        if (LoginType.MOBILE.name().equalsIgnoreCase(sysUserLoginDTO.getLoginType())) {
            clazz = ValidatorGroup.LoginMobile.class;
        }
        ResultInfo<?> validateResult = ValidationUtils.validate(sysUserLoginDTO, clazz);
        if (!validateResult.isSuccess()) {
            return validateResult;
        }

        // 获取令牌
        ResultInfo<OauthAccessTokenVO> result = oauth2Service.getAccessToken(
            Oauth2Constants.GrantType.PASSWORD.name().toLowerCase(), sysUserLoginDTO.getUsername(),
            sysUserLoginDTO.getPassword(), Oauth2Constants.Scope.USERINFO.name().toLowerCase(),
            oauth2Properties.getClientId(), oauth2Properties.getClientSecret());
        if (!result.isSuccess()) {
            return result;
        }
        return doLogin(request, result.getPayload());
    }

    /**
     * Oauth2 > 授权码模式 认证授权成功后回调后进行本地登录
     *
     * @param code 授权码
     * @return 登录用户信息
     */
    @RequestMapping("/login")
    public ResultInfo<?> login(HttpServletRequest request, String code) {
        // 获取令牌
        ResultInfo<OauthAccessTokenVO> result =
            oauth2Service.getAccessToken(Oauth2Constants.GrantType.AUTHORIZATION_CODE.name().toLowerCase(), code,
                oauth2Properties.getClientId(), oauth2Properties.getClientSecret());
        if (!result.isSuccess()) {
            return result;
        }
        return doLogin(request, result.getPayload());
    }

    private ResultInfo<?> doLogin(HttpServletRequest request, OauthAccessTokenVO oauthAccessTokenVO) {
        // 获取用户信息
        ResultInfo<OauthUserVO> result =
            oauth2Service.getUserInfo(oauthAccessTokenVO.getAccessToken(), oauthAccessTokenVO.getOpenId());
        if (!result.isSuccess()) {
            return result;
        }
        OauthUserVO oauthUserVO = result.getPayload();

        String clientIp = ServletUtil.getClientIP(request);
        String userAgent = ServletUtil.getHeader(request, "user-agent", StandardCharsets.UTF_8);

        // 本地登录
        ResultInfo<?> loginResult = sysUserService.login(oauthUserVO.getOpenId(), clientIp, userAgent);
        if (!loginResult.isSuccess()) {
            return loginResult;
        }
        SysUserEntity sysUserEntity = (SysUserEntity)loginResult.getPayload();
        SysUserLoginVO sysUserLoginVO = BeanUtil.copyProperties(sysUserEntity, SysUserLoginVO.class);
        // 获取登录Token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        sysUserLoginVO.setAccessToken(tokenInfo.getTokenValue());
        sysUserLoginVO.setTokenTimeout(tokenInfo.getTokenTimeout());
        sysUserLoginVO.setSessionTimeout(tokenInfo.getSessionTimeout());
        sysUserLoginVO.setTokenSessionTimeout(tokenInfo.getTokenSessionTimeout());
        sysUserLoginVO.setTokenActivityTimeout(tokenInfo.getTokenActivityTimeout());

        return ResultInfo.ok(sysUserLoginVO);
    }
}
