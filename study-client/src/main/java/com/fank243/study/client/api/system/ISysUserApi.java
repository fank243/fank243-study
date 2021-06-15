package com.fank243.study.client.api.system;

import com.fank243.study.client.domain.PageBean;
import com.fank243.study.client.domain.system.dto.SysUserDTO;
import com.fank243.study.client.domain.system.vo.SysUserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理员网关
 * 
 * @author FanWeiJie
 * @since 2021-06-14 17:05:42
 */
@RequestMapping("/sys/user")
public interface ISysUserApi {

    /**
     * 系统管理员列表
     * 
     * @param sysUser 用户实体
     * @return 用户实体
     */
    @PostMapping("/list")
    PageBean<SysUserVO> pageOfSysUser(@RequestBody SysUserDTO sysUser);
}
