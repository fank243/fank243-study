package com.fank243.study.oauth2.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dev33.satoken.util.SaFoxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.common.core.utils.ServletUtils;
import com.fank243.study.oauth2.api.domain.entity.OauthUserEntity;
import com.fank243.study.oauth2.api.domain.vo.OauthAccessTokenVO;
import com.fank243.study.oauth2.api.domain.vo.OauthUserVO;
import com.fank243.study.oauth2.service.OauthUserService;

import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Consts;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Util;
import cn.dev33.satoken.oauth2.model.AccessTokenModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.StrUtil;
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

    @RequestMapping({"", "/index"})
    public String index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/error/401");
        return null;
    }

    /** 请求统一入口 **/
    @SuppressWarnings("unchecked")
    @RequestMapping("/oauth2/*")
    public Object request(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        // oauth2 认证入口
        Object obj = SaOAuth2Handle.serverRequest();
        if (obj instanceof SaResult saResult) {
            if (saResult.getCode() != SaResult.CODE_SUCCESS) {
                ServletUtils.renderJson(response, ResultInfo.fail(saResult.getMsg()));
                return null;
            }
            if (saResult.getData() != null) {
                Map<String, Object> data = (Map<String, Object>)saResult.getData();
                OauthAccessTokenVO oauthAccessTokenVO = OauthAccessTokenVO.builder()
                    .openId(String.valueOf(data.get("openid"))).clientId(String.valueOf(data.get("client_id")))
                    .scope(String.valueOf(data.get("scope"))).accessToken(String.valueOf(data.get("access_token")))
                    .refreshToken(String.valueOf(data.get("refresh_token")))
                    .expiresIn(String.valueOf(data.get("expires_in")))
                    .refreshExpiresIn(String.valueOf(data.get("refresh_expires_in"))).build();
                ServletUtils.renderJson(response, ResultInfo.ok(saResult.getMsg(), oauthAccessTokenVO));
                return null;
            }
            ServletUtils.renderJson(response, ResultInfo.ok().message(saResult.getMsg()));
            return null;
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder("?");
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()[0]).append("&");
        }
        String params = StrUtil.replaceLast(sb.toString(), "&", "");

        // 登录页面
        if (StrUtil.equalsIgnoreCase("login", String.valueOf(obj))) {
            modelMap.put("redirectUri", request.getRequestURL().toString() + params);
            return "login";
        }
        // 确认授权页面
        else if (StrUtil.equalsIgnoreCase("confirm", String.valueOf(obj))) {
            return "confirm";
        }

        ServletUtils.renderJson(response, ResultInfo.fail(String.valueOf(obj)));
        return null;
    }

    @Autowired
    public void oauth2Config(SaOAuth2Config cfg, HttpServletRequest request) {
        cfg
            // 配置：未登录时返回的View
            .setNotLoginView(() -> "login")
            // 配置：登录处理函数
            .setDoLoginHandle((name, pwd) -> {
                String userId;
                try {
                    userId = oauthUserService.login(name, pwd);
                } catch (AuthException e) {
                    e.printStackTrace();
                    return SaResult.error(e.getMessage());
                }
                StpUtil.login(userId, "PC");
                return SaResult.ok();
            })
            // 配置：确认授权时返回的View
            .setConfirmView((clientId, scope) -> {
                request.setAttribute("clientId", clientId);
                request.setAttribute("scope", scope);
                return "confirm";
            });
        // 开启密码认证模式
        cfg.setIsPassword(true);
    }

    @ExceptionHandler
    public String handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String accept = request.getHeader("accept");
        log.error("统一认证异常：{}", e.getMessage(), e);
        if (StrUtil.contains(accept, "text/html")) {
            response.sendRedirect("/error/500?message=" + URLEncodeUtil.encode(e.getMessage()));
        } else {
            ServletUtils.renderJson(response, ResultInfo.error(e.getMessage(), e.toString()));
        }
        return null;
    }
}
