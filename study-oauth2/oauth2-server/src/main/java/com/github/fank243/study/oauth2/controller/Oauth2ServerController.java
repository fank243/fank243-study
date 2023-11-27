package com.github.fank243.study.oauth2.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.HttpConstants;
import com.github.fank243.study.core.constants.enums.EnvEnum;
import com.github.fank243.study.core.model.redis.RedisService;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.github.fank243.study.core.properties.StudyProperties;
import com.github.fank243.study.core.utils.WebUtils;
import com.github.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.study.oauth2.api.domain.dto.OauthAccessTokenDTO;
import com.github.fank243.study.oauth2.api.domain.dto.OauthLoginDTO;
import com.github.fank243.study.oauth2.service.OauthUserService;

import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Consts;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Sa-OAuth2 Server端 控制器
 * 
 * @author FanWeiJie
 * @since 2021-11-24 16:26:32
 */
@Slf4j
@Controller
public class Oauth2ServerController {

    @Resource
    private OauthUserService oauthUserService;
    @Resource
    private RedisService redisService;
    @Resource
    private SaOAuth2Config cfg;

    /** 请求统一入口 **/
    @SuppressWarnings("unchecked")
    @RequestMapping("/oauth2/*")
    public Object request(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // oauth2 认证入口
        Object obj = SaOAuth2Handle.serverRequest();
        if (obj instanceof SaResult saResult) {
            if (saResult.getCode() != SaResult.CODE_SUCCESS) {
                WebUtils.renderJson(response, ResultInfo.err500(saResult.getMsg()));
                return null;
            }
            if (saResult.getData() != null) {
                Map<String, Object> data = (Map<String, Object>)saResult.getData();
                OauthAccessTokenDTO oauthAccessTokenDTO = OauthAccessTokenDTO.builder()
                    .openId(String.valueOf(data.get("openid"))).clientId(String.valueOf(data.get("client_id")))
                    .scope(String.valueOf(data.get("scope"))).accessToken(String.valueOf(data.get("access_token")))
                    .refreshToken(String.valueOf(data.get("refresh_token")))
                    .expiresIn(String.valueOf(data.get("expires_in")))
                    .refreshExpiresIn(String.valueOf(data.get("refresh_expires_in"))).build();
                WebUtils.renderJson(response, ResultInfo.ok(saResult.getMsg(), oauthAccessTokenDTO));
                return null;
            }
            WebUtils.renderJson(response, ResultInfo.ok().message(saResult.getMsg()));
            return null;
        }

        // 登录页面
        if (StrUtil.equalsIgnoreCase(Oauth2Constants.LOGIN, String.valueOf(obj))) {
            return "login";
        }
        // 确认授权页面
        else if (StrUtil.equalsIgnoreCase(Oauth2Constants.CONFIRM, String.valueOf(obj))) {
            return "confirm";
        }
        // 404
        else if (StrUtil.equalsIgnoreCase(SaOAuth2Consts.NOT_HANDLE, String.valueOf(obj))) {
            if (WebUtils.acceptTextHtml(request.getHeader(HttpHeaders.ACCEPT))) {
                response.sendRedirect(StudyProperties.baseUrl + HttpConstants.ERROR_404);
                return null;
            }
        }

        WebUtils.renderJson(response, ResultInfo.err500(String.valueOf(obj)));
        return null;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    /** 登录接口 **/
    @PostMapping("/oauth2/login")
    @ResponseBody
    public ResultInfo<?>
        login(@RequestBody @Validated(value = ValidatorGroup.Login.class) OauthLoginDTO oauthLoginDTO) {
        if (EnvEnum.PROD.name().equalsIgnoreCase(SpringUtil.getActiveProfile())) {
            String key = CacheConstants.IMG_CODE_KEY + oauthLoginDTO.getRandomStr();
            Object imgCodeObj = redisService.getObj(key);
            redisService.delete(key);
            if (ObjectUtil.isEmpty(imgCodeObj)
                || !oauthLoginDTO.getImgCode().equalsIgnoreCase(String.valueOf(imgCodeObj))) {
                return ResultInfo.err400("图形验证码不正确");
            }
        }

        // sa-token 登录
        SaResult saResult =
            (SaResult)cfg.getDoLoginHandle().apply(oauthLoginDTO.getUsername(), oauthLoginDTO.getPassword());
        if (saResult.getCode() != SaResult.CODE_SUCCESS) {
            return ResultInfo.err500(saResult.getMsg());
        }
        return ResultInfo.ok();
    }

    @Autowired
    public void oauth2Config(SaOAuth2Config cfg, HttpServletRequest request) {
        cfg
            // 配置：未登录时返回的View
            .setNotLoginView(() -> "login")
            // 配置：登录处理函数
            .setDoLoginHandle((name, pwd) -> {
                ResultInfo<String> result = oauthUserService.login(name, pwd);
                if (!result.isSuccess()) {
                    return SaResult.error(result.getMessage());
                }
                StpUtil.login(result.getPayload(), "PC");
                return SaResult.ok();
            })
            // 配置：确认授权时返回的View
            .setConfirmView((clientId, scope) -> {
                request.setAttribute("clientId", clientId);
                request.setAttribute("scope", scope);
                return "confirm";
            });
        // 开启密码认证模式
        cfg.setIsPassword(Boolean.TRUE);
    }

}
