package com.fank243.study.system.dao;

import com.fank243.study.system.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统管理员表 数据访问层
 * </p>
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Repository
public interface ISysUserDao extends BaseMapper<SysUserEntity> {

}
