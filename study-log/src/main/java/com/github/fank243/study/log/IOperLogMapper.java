package com.github.fank243.study.log;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 操作日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Mapper
public interface IOperLogMapper extends BaseMapper<OperLogEntity> {

}
