package com.github.fank243.study.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fank243.study.oauth2.api.domain.entity.OauthAccessTokenEntity;

/**
 * 授权令牌 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
public interface IOauthAccessTokenDao extends BaseMapper<OauthAccessTokenEntity> {

}
