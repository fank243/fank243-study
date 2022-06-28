package com.fank243.study.gateway.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fank243.study.gateway.domain.SysPermVO;
import com.fank243.study.gateway.domain.SysRoleVO;
import com.fank243.study.gateway.service.SysPermissionService;
import com.fank243.study.gateway.service.SysRoleService;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;

/**
 * saToken注册统一鉴权
 * 
 * @author FanWeiJie
 * @since 2022-05-11 10:46:17
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysPermissionService sysPermissionService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        List<SysPermVO> perms = sysPermissionService.findByUserId((String)loginId);
        if (CollUtil.isEmpty(perms)) {
            return null;
        }
        return perms.stream().map(SysPermVO::getPermCode).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        List<SysRoleVO> roles = sysRoleService.findByUserId((String)loginId);
        if (CollUtil.isEmpty(roles)) {
            return null;
        }
        return roles.stream().map(SysRoleVO::getRoleCode).collect(Collectors.toList());
    }

}
