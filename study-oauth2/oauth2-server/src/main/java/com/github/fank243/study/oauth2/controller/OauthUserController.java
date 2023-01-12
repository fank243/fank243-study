package com.github.fank243.study.oauth2.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.core.constants.ValidatorGroup;
import com.github.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserDTO;
import com.github.fank243.study.oauth2.api.domain.entity.OauthUserEntity;
import com.github.fank243.study.oauth2.api.domain.vo.OauthUserVO;
import com.github.fank243.study.oauth2.service.OauthUserService;

import cn.dev33.satoken.oauth2.logic.SaOAuth2Util;
import cn.dev33.satoken.oauth2.model.AccessTokenModel;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户接口
 * 
 * @author FanWeiJie
 * @since 2022-10-03 08:48:29
 */
@Slf4j
@RequestMapping(ServerConstants.BASE_URI_OAUTH2)
@RestController
public class OauthUserController {

    @Resource
    private OauthUserService oauthUserService;

    private ResultInfo<?> validate(String accessToken, String openId) {
        AccessTokenModel accessTokenModel;
        try {
            accessTokenModel = SaOAuth2Util.checkAccessToken(accessToken);
            if (!openId.equalsIgnoreCase(accessTokenModel.openid)) {
                return ResultInfo.err403("invalid openid");
            }
        } catch (Exception e) {
            return ResultInfo.err403(e.getMessage());
        }

        return ResultInfo.ok(accessTokenModel);
    }

    private ResultInfo<?> validateUserModify(String accessToken, String openId) {
        ResultInfo<?> result = validate(accessToken, openId);
        if (!result.isSuccess()) {
            return result;
        }

        // 验证用户是否具有修改用户权限
        SaOAuth2Util.checkScope(accessToken, Oauth2Constants.Scope.USER_MODIFY.name().toLowerCase());

        return result;
    }

    /** 获取用户信息 **/
    @GetMapping({"/users/info"})
    public ResultInfo<?> userInfo(String accessToken, String openId) {
        ResultInfo<?> result = validate(accessToken, openId);
        if (!result.isSuccess()) {
            return result;
        }
        AccessTokenModel accessTokenModel = (AccessTokenModel)result.getPayload();

        OauthUserEntity oauthUserEntity = oauthUserService.findByUserId(String.valueOf(accessTokenModel.loginId));
        OauthUserVO oauthUserVO = BeanUtil.copyProperties(oauthUserEntity, OauthUserVO.class);
        oauthUserVO.setOpenId(openId);

        return ResultInfo.ok(oauthUserVO);
    }

    /** 获取用户信息 **/
    @PostMapping({"/users/add"})
    public ResultInfo<?> addUser(@RequestBody @Validated(ValidatorGroup.Create.class) OauthUserDTO oauthUserDTO) {
        ResultInfo<?> result = validateUserModify(oauthUserDTO.getAccessToken(), oauthUserDTO.getOpenId());
        if (!result.isSuccess()) {
            return result;
        }
        AccessTokenModel accessTokenModel = (AccessTokenModel)result.getPayload();

        return oauthUserService.addUser(accessTokenModel.clientId, oauthUserDTO);
    }

    /** 修改用户密码 **/
    @PostMapping({"/users/password"})
    public ResultInfo<?>
        modifyPassword(@RequestBody @Validated(ValidatorGroup.Modify.class) OauthUserDTO oauthUserDTO) {
        ResultInfo<?> result = validateUserModify(oauthUserDTO.getAccessToken(), oauthUserDTO.getOpenId());
        if (!result.isSuccess()) {
            return result;
        }
        AccessTokenModel accessTokenModel = (AccessTokenModel)result.getPayload();

        result = oauthUserService.modifyPassword(String.valueOf(accessTokenModel.loginId), oauthUserDTO);
        if (result.isSuccess()) {
            return result;
        }

        // 回收令牌
        SaOAuth2Util.revokeAccessToken(oauthUserDTO.getAccessToken());

        return result;
    }

}
