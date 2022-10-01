package com.fank243.study.support.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.support.domain.entity.ReqRespLogEntity;

/**
 * 请求响应日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Mapper
public interface IReqRespLogMapper extends BaseMapper<ReqRespLogEntity> {

}
