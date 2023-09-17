package com.github.fank243.study.oauth2.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.String;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.oauth2.entity.OauthAccessTokenEntity;

/**
 * 用户授权令牌表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
public interface IOauthAccessTokenMapper extends BaseMapper<OauthAccessTokenEntity> {

     /**
     * 根据主键ID查询
     *
     * @param tokenId 主键
     * @return 用户授权令牌表
     */
     OauthAccessTokenEntity findByTokenId(String tokenId);

     /**
     * 多条件查询
     *
     * @param oauthAccessToken 条件参数
     * @return 用户授权令牌表
     */
     OauthAccessTokenEntity findByCondition(@Param("oauthAccessToken") OauthAccessTokenEntity oauthAccessToken);

     /**
     * 多条件查询
     *
     * @param oauthAccessToken 条件参数
     * @return 用户授权令牌表
     */
     List<OauthAccessTokenEntity> findListByCondition(@Param("oauthAccessToken") OauthAccessTokenEntity oauthAccessToken);
}
