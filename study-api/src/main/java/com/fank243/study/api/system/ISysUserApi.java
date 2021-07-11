package com.fank243.study.api.system;

import com.fank243.study.api.system.constants.SystemApiConstants;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.core.utils.ResultInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 系统管理员网关
 * 
 * @author FanWeiJie
 * @since 2021-06-14 17:05:42
 */
public interface ISysUserApi {

    /**
     * 系统管理员列表
     * 
     * @param id ID
     * @return 用户实体
     */
    @GetMapping(SystemApiConstants.BASE_URI + "/get/{id}")
    ResultInfo<SysUserVO> getById(@PathVariable("id") String id) ;

    /**
     * 系统管理员列表
     * 
     * @param sysUser 用户实体
     * @return 用户实体
     */
    @PostMapping(SystemApiConstants.BASE_URI + "/page")
    ResultInfo<PageBean<SysUserVO>> pageOfSysUser(@RequestBody SysUserDTO sysUser);
}
