package com.github.fank243.study.oauth2.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.String;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.oauth2.entity.OauthClientEntity;

/**
 * Oauth2认证客户端表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
public interface IOauthClientMapper extends BaseMapper<OauthClientEntity> {

     /**
     * 根据主键ID查询
     *
     * @param clientId 主键
     * @return Oauth2认证客户端表
     */
     OauthClientEntity findByClientId(String clientId);

     /**
     * 多条件查询
     *
     * @param oauthClient 条件参数
     * @return Oauth2认证客户端表
     */
     OauthClientEntity findByCondition(@Param("oauthClient") OauthClientEntity oauthClient);

     /**
     * 多条件查询
     *
     * @param oauthClient 条件参数
     * @return Oauth2认证客户端表
     */
     List<OauthClientEntity> findListByCondition(@Param("oauthClient") OauthClientEntity oauthClient);
}
