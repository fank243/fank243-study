package com.fank243.study.gateway.web.config;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fank243.study.api.system.api.ISysPermApi;
import com.fank243.study.api.system.api.ISysRoleApi;
import com.fank243.study.api.system.domain.vo.SysPermVO;
import com.fank243.study.api.system.domain.vo.SysRoleVO;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;

/**
 * saToken注册统一鉴权
 * 
 * @author FanWeiJie
 * @since 2022-05-11 10:46:17
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private ISysRoleApi sysRoleApi;
    @Resource
    private ISysPermApi sysPermApi;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        Future<List<SysPermVO>> future = ThreadUtil.execAsync(() -> sysPermApi.getByUserId((String)loginId));
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
        Future<List<SysRoleVO>> future = ThreadUtil.execAsync(() -> sysRoleApi.getByUserId((String)loginId));
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
