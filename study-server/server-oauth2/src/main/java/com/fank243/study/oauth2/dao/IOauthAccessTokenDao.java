package com.fank243.study.oauth2.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.oauth2.domain.entity.OauthAccessTokenEntity;

/**
 * 系统管理员角色关联表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Mapper
public interface IOauthAccessTokenDao extends BaseMapper<OauthAccessTokenEntity> {

}
