package com.fank243.study.system.controller;

import com.fank243.study.client.api.system.SysUserApi;
import com.fank243.study.client.context.system.SysUserContext;
import com.fank243.study.system.service.ISysUserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制器
 * 
 * @author FanWeiJie
 * @since 2021-06-13 23:59:03
 */
@RestController
public class SysUserController implements SysUserApi {

    @Resource
    private ISysUserService sysUserService;

    @Override
    public List<SysUserContext> list(String username) {
        return sysUserService.findAll();
    }
}
