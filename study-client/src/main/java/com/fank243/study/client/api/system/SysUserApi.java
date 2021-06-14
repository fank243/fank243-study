package com.fank243.study.client.api.system;

import com.fank243.study.client.context.system.SysUserContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 系统管理员网关
 * 
 * @author FanWeiJie
 * @since 2021-06-14 17:05:42
 */
@RequestMapping("/sys/user")
public interface SysUserApi {

    /**
     * 系统管理员列表
     * 
     * @param username 用户名
     * @return 系统管理员列表
     */
    @PostMapping("/list")
    List<SysUserContext> list(@RequestParam("username") String username);
}
