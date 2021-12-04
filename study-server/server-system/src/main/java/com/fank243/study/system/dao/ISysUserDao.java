package com.fank243.study.system.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.system.entity.SysUserEntity;

/**
 * 系统管理员表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Repository
public interface ISysUserDao extends BaseMapper<SysUserEntity> {

}
