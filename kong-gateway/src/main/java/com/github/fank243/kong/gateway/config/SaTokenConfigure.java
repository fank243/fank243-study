/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.gateway.config;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.github.fank243.kong.tool.result.ResultCodeEnum;
import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.system.domain.enums.PermTypeEnum;
import com.github.fank243.kong.system.domain.vo.SysPermVO;
import com.github.fank243.kong.system.service.ISysPermService;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SameTokenInvalidException;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * [Sa-Token 权限认证] 全局配置类
 *
 * @author FanWeiJie
 * @since 2021-11-24 13:36:23
 */
@Slf4j
@Configuration
public class SaTokenConfigure {

    @Resource
    private ISysPermService sysPermService;

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
            JsonTypeInfo.As.WRAPPER_ARRAY);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(mapper, Object.class);

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        redisCacheConfiguration = redisCacheConfiguration
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

        if (redisProperties.getTimeToLive() != null) {
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            redisCacheConfiguration = redisCacheConfiguration.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
        }
        return redisCacheConfiguration;
    }

    /** 注册 [Sa-Token全局过滤器] */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
            // 指定 [拦截路由]
            .addInclude("/**")
            // 指定 [放行路由]
            .addExclude("/favicon.ico", "/static/**", "/error/**", "/oauth2/**", "/auth/**")
            // 指定[认证函数]: 每次请求执行
            .setAuth(obj -> {
                // HTTP METHOD
                SaRouter.notMatch(SaHttpMethod.OPTIONS, SaHttpMethod.GET, SaHttpMethod.PUT, SaHttpMethod.POST,
                    SaHttpMethod.DELETE).check(() -> {
                        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "非法请求");
                    });

                SaRouter.match("/api/**", StpUtil::checkLogin);
                // 文件浏览
                // SaRouter.match("/file/**", StpUtil::checkLogin);
                // swagger ui
                SaRouter.match("/support/**", StpUtil::checkLogin);
                // 登录接口
                SaRouter.notMatch("/system/login/**").match("/system/**", StpUtil::checkLogin);

                List<SysPermVO> perms;
                try {
                    Future<List<SysPermVO>> future =
                            ThreadUtil.execAsync(() -> sysPermService.getByPermTypes(PermTypeEnum.PERMS));
                    perms = future.get();
                } catch (InterruptedException | ExecutionException e) {
					log.error("获取登录用户所有权限异常：{}", e.getMessage(), e);
                    throw new RuntimeException(e);
                }
                // 菜单、接口权限
                if (CollUtil.isNotEmpty(perms)) {
                    for (SysPermVO perm : perms) {
                        if (StrUtil.isBlank(perm.getPermUri())) {
                            continue;
                        }
                        SaRouter.match("/api" + perm.getPermUri(), r -> StpUtil.checkPermission(perm.getPermCode()));
                    }
                }

            })
            // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数
            .setError((e) -> {
                log.error("认证异常：{}", e.getMessage(), e);
                if (e instanceof NotLoginException) {
                    return response(ResultInfo.err401().error(e.getMessage()));
                } else if (e instanceof NotRoleException || e instanceof NotPermissionException
                    || e instanceof SameTokenInvalidException) {
                    return response(ResultInfo.err403().error(e.getMessage()));
                } else if (e instanceof ResponseStatusException ex) {
                    ResultInfo<?> result;
                    if (org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED.value() == ex.getStatusCode().value()) {
                        result = ResultInfo.err405(ex.getReason()).error(e.getMessage());
                    } else {
                        result = ResultInfo.error(ex.getStatusCode().value(), ex.getReason()).error(e.getMessage());
                    }
                    return response(result);
                }
                return response(ResultInfo.err500("Oauth2认证异常").error(e.getMessage()));
            })
            // 前置函数：在每次认证函数之前执行
            .setBeforeAuth(r -> SaHolder.getResponse()
                // 服务器名称
                // .setServer("sa-server")
                // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                .setHeader("X-Frame-Options", "SAMEORIGIN")
                // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                .setHeader("X-XSS-Protection", "1; mode=block")
                // 禁用浏览器内容嗅探
                .setHeader("X-Content-Type-Options", "nosniff"));
    }

    private ResultInfo<?> response(ResultInfo<?> resultInfo) {
        SaRequest request = SaHolder.getRequest();
        SaResponse response = SaHolder.getResponse();
        boolean isHtml = request.getHeader(HttpHeaders.ACCEPT).contains(MediaType.TEXT_HTML_VALUE);
        if (isHtml && HttpStatus.UNAUTHORIZED.value() == resultInfo.getStatus()) {
            String url = request.getUrl();
            if (url.contains("/oauth") || url.contains("/login")) {
                response.redirect("/login");
            } else {
                response.redirect("/login?redirect=" + url);
            }
            return null;
        }
        if (ResultCodeEnum.R401.getStatus() == resultInfo.getStatus()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return resultInfo;
    }
}
