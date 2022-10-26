package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fank243.study.system.domain.entity.SysUserLoginLogEntity;

/**
 * 系统管理员登录日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Mapper
public interface ISysUserLoginLogMapper extends BaseMapper<SysUserLoginLogEntity> {

}
