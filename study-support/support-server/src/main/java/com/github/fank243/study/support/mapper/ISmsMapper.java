package com.github.fank243.study.support.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.fank243.study.support.domain.SmsEntity;
import com.mybatisflex.core.BaseMapper;

/**
 * 短信发送记录表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-20 10:46:43
 */
@Mapper
public interface ISmsMapper extends BaseMapper<SmsEntity> {}
