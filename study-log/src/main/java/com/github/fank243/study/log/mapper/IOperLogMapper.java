package com.github.fank243.study.log.mapper;

import com.github.fank243.study.log.OperLogEntity;
import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.BaseMapper;

/**
 * 操作日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@UseDataSource("support")
public interface IOperLogMapper extends BaseMapper<OperLogEntity> {

}
