package com.fank243.study.gateway.web.filter;

import javax.annotation.Resource;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.common.core.constants.RedisConstants;
import com.fank243.study.common.core.constants.TimeConstant;
import com.fank243.study.common.core.service.RedisService;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.gateway.config.Oauth2Properties;
import com.fank243.study.gateway.constants.FilterOrderConstant;
import com.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.fank243.study.oauth2.api.domain.vo.OauthAccessTokenVO;
import com.fank243.study.oauth2.api.service.IOauth2Service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Oauth2 访问令牌自动刷新拦截器
 * 
 * @author FanWeiJie
 * @since 2022-10-03 11:33:02
 */
@Slf4j
@Component
public class TokenRefreshFilter implements GlobalFilter, Ordered {

    @Resource
    private IOauth2Service oauth2Service;
    @Resource
    private RedisService redisService;
    @Resource
    private Oauth2Properties oauth2Properties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (!request.getURI().getPath().startsWith("/api")) {
            return chain.filter(exchange);
        }
        if (!StpUtil.isLogin()) {
            return chain.filter(exchange);
        }
        String userId = StpUtil.getLoginIdAsString();
        String key = RedisConstants.OAUTH2_TOKEN + userId;
        if (redisService.getExpire(key) > TimeConstant.MINUTE_5) {
            return chain.filter(exchange);
        }
        Object obj = redisService.getObj(key);
        if (obj instanceof OauthAccessTokenVO oauthAccessTokenVO) {
            // 有请求就刷新令牌
            ThreadUtil.execute(() -> {
                ResultInfo<OauthAccessTokenVO> result = oauth2Service.refreshToken(
                    Oauth2Constants.GrantType.REFRESH_TOKEN.name().toLowerCase(), oauthAccessTokenVO.getRefreshToken(),
                    oauth2Properties.getClientId(), oauth2Properties.getClientSecret());
                if (!result.isSuccess()) {
                    log.info("【令牌刷新拦截器】刷新令牌失败：{}", result);
                } else {
                    // 覆写redis
                    redisService.setObj(key, result.getPayload(), TimeConstant.MINUTE_30);
                }
            });
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }

}
