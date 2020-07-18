package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.BaseServiceTest;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.entity.SysUser;
import com.fank243.springboot.core.enums.RoleType;
import org.junit.Test;

import javax.annotation.Resource;

public class SysSysRoleServiceTest extends BaseServiceTest {

    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void addRole() {
        SysRole sysRole = new SysRole();
        sysRole.setName("role:root");
        sysRole.setDescription("超级管理员");
        ResultInfo result = sysRoleService.addOrModify(0L, sysRole);
        System.out.println(result);
    }

    @Test
    public void modifyRole() {
        // ResultInfo result = sysRoleService.modifyRole(1L, 1L, RoleType.ADMIN.name(), "普通管理员2");
        // System.out.println(result);
    }

    @Test
    public void deleteRole() {
        ResultInfo result = sysRoleService.deleteRole(1L, 1L);
        System.out.println(result);
    }

    @Test
    public void authorize() {
        ResultInfo result = sysRoleService.authorize(0L, 1L, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        System.out.println(result);
    }
}