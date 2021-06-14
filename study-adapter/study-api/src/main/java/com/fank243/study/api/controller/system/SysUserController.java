package com.fank243.study.api.controller.system;

import com.fank243.study.api.feign.system.SysUserFeignClient;
import com.fank243.study.client.context.system.SysUserContext;
import com.fank243.study.core.utils.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @date 2021-06-09 00:11:14
 */
@RequestMapping("/client/sys/user")
@RestController
public class SysUserController {

    @Resource
    private SysUserFeignClient sysUserFeignClient;

    @PostMapping("/list")
    public ResultInfo<List<SysUserContext>> list(String username) {
        return ResultInfo.ok(sysUserFeignClient.list(username));
    }
}
