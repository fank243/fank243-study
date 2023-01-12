package com.github.fank243.study.gateway.config;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.domain.enums.PermTypeEnum;
import com.github.fank243.study.system.domain.vo.SysPermVO;
import com.github.fank243.study.system.service.ISysPermService;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
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
            .addExclude("/favicon.ico", "/static/**")
            // 指定[认证函数]: 每次请求执行
            .setAuth(obj -> {
                // HTTP METHOD
                SaRouter.notMatch(SaHttpMethod.OPTIONS, SaHttpMethod.GET, SaHttpMethod.PUT, SaHttpMethod.POST,
                    SaHttpMethod.DELETE).check(() -> SaTokenException.throwByNull(null, "请求方法非法", 405));

                SaRouter.match("/api/**", StpUtil::checkLogin);

                Future<List<SysPermVO>> future =
                    ThreadUtil.execAsync(() -> sysPermService.getByPermTypes(PermTypeEnum.PERMS));
                List<SysPermVO> perms;
                try {
                    perms = future.get();
                } catch (InterruptedException | ExecutionException e) {
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
                    return ResultInfo.err401();
                } else if (e instanceof NotRoleException || e instanceof NotPermissionException
                    || e instanceof SameTokenInvalidException) {
                    return ResultInfo.err403().error(e.getMessage());
                }
                return ResultInfo.err500("oauth2认证异常").error(e.getMessage());
            })
            // 前置函数：在每次认证函数之前执行
            .setBeforeAuth(r -> SaHolder.getResponse()
                // 服务器名称
                .setServer("sa-server")
                // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                .setHeader("X-Frame-Options", "SAMEORIGIN")
                // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                .setHeader("X-XSS-Protection", "1; mode=block")
                // 禁用浏览器内容嗅探
                .setHeader("X-Content-Type-Options", "nosniff"));
    }
}
