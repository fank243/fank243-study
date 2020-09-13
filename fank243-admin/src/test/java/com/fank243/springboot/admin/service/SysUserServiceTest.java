package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.BaseServiceTest;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.DictConsts;
import com.fank243.springboot.core.entity.SysUser;
import org.junit.Test;

import javax.annotation.Resource;

public class SysUserServiceTest extends BaseServiceTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void addAdmin() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setRealname("张三");
        sysUser.setWxNumber("j129");
        sysUser.setMobile("13212345678");
        sysUser.setStatus(DictConsts.SYS_USER_STATUS_ENABLE);
        String salt = StrUtils.randomStr(20);
        sysUser.setSalt(salt);
        sysUser.setPassword(ShiroUtils.md5WithSalt(salt, "123456"));
        ResultInfo result = sysUserService.addOrModify(0L, sysUser, "1");
        // ResultInfo result = sysUserService.addAdmin(StrUtils.randomStr(6), "186" + StrUtils.randomNum(8), "123456");
        System.out.println(result);
    }

    @Test
    public void deleteAdmin() {
        ResultInfo result = sysUserService.deleteAdmin(2L);
        System.out.println(result);
    }

    @Test
    public void modifyPassword() {
        // ResultInfo result = sysUserService.modifyPassword(2L, "123456", "123456");
        // System.out.println(result);
    }

    @Test
    public void login() {
        ResultInfo result = sysUserService.login("admin", "123456", "ddlk");
        System.out.println(result);
    }
}