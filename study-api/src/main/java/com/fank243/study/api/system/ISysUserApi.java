package com.fank243.study.api.system;

import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.model.PageBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统管理员网关
 * 
 * @author FanWeiJie
 * @since 2021-06-14 17:05:42
 */
public interface ISysUserApi {

    String BASE_URI = "/sys/user";

    /**
     * 系统管理员列表
     * 
     * @param id ID
     * @return 用户实体
     */
    @GetMapping(BASE_URI + "/get/{id}")
    SysUserVO getById(@PathVariable("id") String id);

    /**
     * 系统管理员列表
     * 
     * @param sysUser 用户实体
     * @return 用户实体
     */
    @PostMapping(BASE_URI + "/page")
    PageBean<SysUserVO> pageOfSysUser(@RequestBody SysUserDTO sysUser);
}
