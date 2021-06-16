package com.fank243.study.server.system.controller;

import com.fank243.study.api.system.ISysUserApi;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.server.system.service.ISysUserService;
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
    public SysUserVO getById(String id) {
        return sysUserService.findById(id);
    }

    @Override
    public PageBean<SysUserVO> pageOfSysUser(SysUserDTO sysUser) {
        return sysUserService.pageOfUser(sysUser);
    }
}
