package com.github.fank243.study.gateway.config;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.fank243.study.system.domain.vo.SysPermVO;
import com.github.fank243.study.system.domain.vo.SysRoleVO;
import com.github.fank243.study.system.service.ISysPermService;
import com.github.fank243.study.system.service.ISysRoleService;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import jakarta.annotation.Resource;

/**
 * saToken注册统一鉴权
 *
 * @author FanWeiJie
 * @since 2022-05-11 10:46:17
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysPermService sysPermService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        Future<List<SysPermVO>> future = ThreadUtil.execAsync(() -> sysPermService.getByUserId((String)loginId));
        List<SysPermVO> perms;
        try {
            perms = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (CollUtil.isEmpty(perms)) {
            return null;
        }
        return perms.stream().map(SysPermVO::getPermCode).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        Future<List<SysRoleVO>> future = ThreadUtil.execAsync(() -> sysRoleService.getByUserId((String)loginId));
        List<SysRoleVO> roles;
        try {
            roles = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (CollUtil.isEmpty(roles)) {
            return null;
        }
        return roles.stream().map(SysRoleVO::getRoleCode).collect(Collectors.toList());
    }

}
