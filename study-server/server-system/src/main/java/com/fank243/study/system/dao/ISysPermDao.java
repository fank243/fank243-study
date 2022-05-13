package com.fank243.study.system.dao;

import com.fank243.study.system.entity.SysPermEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 系统权限表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Repository
public interface ISysPermDao extends BaseMapper<SysPermEntity> {

}
