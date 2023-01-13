package com.github.fank243.study.log.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fank243.study.log.OperLogEntity;

/**
 * 操作日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@DS("support")
public interface IOperLogMapper extends BaseMapper<OperLogEntity> {

}
