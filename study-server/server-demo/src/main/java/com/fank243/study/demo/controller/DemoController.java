package com.fank243.study.demo.controller;

import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.client.system.ISysUserClient;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.core.utils.ResultInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * demo
 * 
 * @author FanWeiJie
 * @since 2021-06-16 21:37:40
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    @Resource
    private ISysUserClient sysUserClient;

    @GetMapping("/get/{id}")
    public ResultInfo<SysUserVO> get(@PathVariable("id") String id) {
        return ResultInfo.ok(sysUserClient.getById(id));
    }

    @PostMapping("/page")
    public ResultInfo<PageBean<SysUserVO>> page(@RequestBody SysUserDTO sysUser) {
        return ResultInfo.ok(sysUserClient.pageOfSysUser(sysUser));
    }
}
