package com.fank243.study.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.system.model.entity.SysUserEntity;
import org.springframework.stereotype.Repository;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-14 00:00:51
 */
@Repository
public interface SysUserDao extends BaseMapper<SysUserEntity> {}
