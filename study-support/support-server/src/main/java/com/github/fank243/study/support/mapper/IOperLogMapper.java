package com.github.fank243.study.support.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fank243.study.support.domain.entity.OperLogEntity;

/**
 * 操作日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Mapper
public interface IOperLogMapper extends BaseMapper<OperLogEntity> {

}
