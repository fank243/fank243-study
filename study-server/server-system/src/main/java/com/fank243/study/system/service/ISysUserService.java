package com.fank243.study.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fank243.study.client.BaseService;
import com.fank243.study.client.domain.PageBean;
import com.fank243.study.client.domain.system.dto.SysUserDTO;
import com.fank243.study.client.domain.system.vo.SysUserVO;
import com.fank243.study.system.domain.entity.SysUserEntity;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-14 00:01:40
 */
public interface ISysUserService extends  IService<SysUserEntity>, BaseService {

    /**
     * 分页查找用户实体
     * 
     * @param sysUser 参数实体
     * @return 用户对象
     */
    PageBean<SysUserVO> pageOfUser(SysUserDTO sysUser);
}
