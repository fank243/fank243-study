package com.fank243.study.server.system.controller;

import com.fank243.study.api.system.ISysUserApi;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.core.utils.ResultInfo;
import com.fank243.study.server.system.service.SysUserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统管理员控制器
 * 
 * @author FanWeiJie
 * @since 2021-06-13 23:59:03
 */
@RestController
public class SysUserController implements ISysUserApi {

    @Resource
    private SysUserService sysUserService;

    @Override
    public ResultInfo<SysUserVO> getById(String id) {
        return ResultInfo.ok(sysUserService.findById(id));
    }

    @Override
    public ResultInfo<PageBean<SysUserVO>> pageOfSysUser(SysUserDTO sysUser) {
        return ResultInfo.ok(sysUserService.pageOfUser(sysUser));
    }
}
