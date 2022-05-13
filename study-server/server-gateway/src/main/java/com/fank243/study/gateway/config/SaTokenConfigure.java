package com.fank243.study.gateway.config;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.gateway.entity.SysPermissionEntity;
import com.fank243.study.gateway.service.SysPermissionService;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
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
    private SysPermissionService sysPermissionService;

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
                    SaHttpMethod.DELETE).check(() -> SaTokenException.throwByNull(null, "请求方法非法"));

                SaRouter.match("/api/**", StpUtil::checkLogin);

                // 菜单权限
                List<SysPermissionEntity> perms = sysPermissionService.findAll();
                if (CollUtil.isNotEmpty(perms)) {
                    for (SysPermissionEntity perm : perms) {
                        SaRouter.match("/api" + perm.getPermUri(), r -> StpUtil.checkPermission(perm.getPermCode()));
                    }
                }
            })
            // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数
            // .setError((e) -> {
            // log.error("认证异常：{}", e.getMessage(), e);
            // if (e instanceof NotLoginException) {
            // return ResultInfo.err401();
            // }
            // return ResultInfo.err403(e.getMessage());
            // })
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
