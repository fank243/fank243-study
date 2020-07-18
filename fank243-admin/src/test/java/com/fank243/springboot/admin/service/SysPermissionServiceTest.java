package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.BaseServiceTest;
import com.fank243.springboot.common.utils.ResultInfo;
import org.junit.Test;

import javax.annotation.Resource;

public class SysPermissionServiceTest extends BaseServiceTest {

    @Resource
    private SysPermissionService sysPermissionService;

    @Test
    public void addRight() {
//        SysPermission sysPermission = new SysPermission();
//        sysPermission.setName("用户管理");
//        sysPermission.setPid(15L);
//        sysPermission.setAvailable(Boolean.TRUE);
//        sysPermission.setExternal(Boolean.FALSE);
//        sysPermission.setType(PermissionType.MENU);
//        sysPermission.setSort(0);
//        sysPermission.setPermission("user:query");
//        sysPermission.setUri("/admin/user");
//        ResultInfo result = sysPermissionService.add(0L, sysPermission);
//        System.out.println(result);
    }

    @Test
    public void modifyRight() {
        // ResultInfo result = sysPermissionService.modifyRight(1L, 2L, 0L, "首页", "/admin/home", "", 0);
        // System.out.println(result);
    }

    @Test
    public void modifyRightStatus() {
//        ResultInfo result = sysPermissionService.modifyStatus(1L, 2L, true);
//        System.out.println(result);
    }

    @Test
    public void deleteRight() {
        ResultInfo result = sysPermissionService.delete(1L, 1L);
        System.out.println(result);
    }
}