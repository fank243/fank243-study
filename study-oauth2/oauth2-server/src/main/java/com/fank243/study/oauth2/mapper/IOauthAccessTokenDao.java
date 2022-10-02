package com.fank243.study.oauth2.mapper;

import com.fank243.study.oauth2.api.domain.entity.OauthAccessTokenEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 授权令牌 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Mapper
public interface IOauthAccessTokenDao extends BaseMapper<OauthAccessTokenEntity> {

}
