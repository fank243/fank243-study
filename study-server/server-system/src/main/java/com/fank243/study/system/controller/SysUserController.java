package com.fank243.study.system.controller;

import com.fank243.study.client.api.system.ISysUserApi;
import com.fank243.study.client.domain.PageBean;
import com.fank243.study.client.domain.system.dto.SysUserDTO;
import com.fank243.study.client.domain.system.vo.SysUserVO;
import com.fank243.study.system.service.ISysUserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 控制器
 * 
 * @author FanWeiJie
 * @since 2021-06-13 23:59:03
 */
@RestController
public class SysUserController implements ISysUserApi {

    @Resource
    private ISysUserService sysUserService;

    @Override
    public PageBean<SysUserVO> pageOfSysUser(SysUserDTO sysUser) {
        return sysUserService.pageOfUser(sysUser);
    }
}
