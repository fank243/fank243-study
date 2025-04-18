package com.github.fank243.kong.oauth2.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.String;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.oauth2.dto.OauthClientDTO;

/**
* Oauth2认证客户端表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface IOauthClientMapper extends BaseMapper<OauthClientEntity> {

    /**
    * 根据主键ID查询
    *
    * @param clientId 主键
    * @return Oauth2认证客户端表
    */
    OauthClientDTO findByClientId(String clientId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return Oauth2认证客户端表
    */
    OauthClientDTO findByCondition(@Param("params") OauthClientDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return Oauth2认证客户端表
    */
    List<OauthClientDTO> findListByCondition(@Param("params") OauthClientDTO params);
}
