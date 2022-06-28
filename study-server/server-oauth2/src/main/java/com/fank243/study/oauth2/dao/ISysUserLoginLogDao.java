package com.fank243.study.oauth2.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.oauth2.domain.SysUserLoginLogEntity;

/**
 * 系统管理员登录日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Repository
public interface ISysUserLoginLogDao extends BaseMapper<SysUserLoginLogEntity> {

}
