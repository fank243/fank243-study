package com.fank243.study.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.system.entity.SysPermissionEntity;
import org.springframework.stereotype.Repository;

/**
 * 系统权限表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Repository
public interface ISysPermissionDao extends BaseMapper<SysPermissionEntity> {

}
