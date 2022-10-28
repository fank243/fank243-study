package com.github.fank243.study.oauth2.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fank243.study.oauth2.api.domain.entity.OauthUserEntity;

/**
 * 授权用户表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Mapper
public interface IOauthUserDao extends BaseMapper<OauthUserEntity> {

}
