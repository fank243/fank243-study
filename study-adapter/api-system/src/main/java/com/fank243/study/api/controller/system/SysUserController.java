package com.fank243.study.api.controller.system;

import com.fank243.study.api.feign.system.SysUserFeignClient;
import com.fank243.study.client.domain.PageBean;
import com.fank243.study.client.domain.system.dto.SysUserDTO;
import com.fank243.study.client.domain.system.vo.SysUserVO;
import com.fank243.study.core.utils.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @date 2021-06-09 00:11:14
 */
@RequestMapping("/system/user")
@RestController
public class SysUserController {

    @Resource
    private SysUserFeignClient sysUserFeignClient;

    @PostMapping("/list")
    public ResultInfo<PageBean<SysUserVO>> list(@RequestBody SysUserDTO sysUser) {
        return ResultInfo.ok(sysUserFeignClient.pageOfSysUser(sysUser));
    }
}
