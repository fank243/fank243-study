package com.fank243.study.gateway.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.gateway.entity.ReqRespLogEntity;

/**
 * 请求响应日志表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-06-28
 */
@Mapper
public interface IReqRespLogDao extends BaseMapper<ReqRespLogEntity> {

}
