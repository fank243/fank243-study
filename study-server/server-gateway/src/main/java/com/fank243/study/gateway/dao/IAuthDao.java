package com.fank243.study.gateway.dao;

import com.fank243.study.gateway.domain.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 认证服务
 * 
 * @author FanWeiJie
 * @since 2021-11-24 14:55:07
 */
@Mapper
public interface IAuthDao extends BaseMapper<SysUserEntity> {}
