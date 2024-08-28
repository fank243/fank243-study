/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.oauth2.repository;

import com.github.fank243.kong.oauth2.api.domain.dto.OauthUserAccessTokenDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.fank243.kong.oauth2.domain.OauthUserEntity;

/**
 * @author FanWeiJie
 * @since 1.2.3
 */
@Repository
public interface IOauthUserRepository extends JpaRepository<OauthUserEntity, Long> {

    /**
     * 根据主键ID查询
     *
     * @param userId 主键
     * @return Oauth2用户表
     */
    OauthUserEntity findByUserId(Long userId);

    OauthUserEntity findByUsername(String username);

    @Query(
        value = """
            select new com.github.fank243.kong.oauth2.api.domain.dto.OauthUserAccessTokenDTO(user.userId,user.username,user.nickname,token.openId) from OauthUserEntity user left join OauthAccessTokenEntity token on user.userId = token.userId where user.username = :username
            """)
    OauthUserAccessTokenDTO findAccessTokenByUsername(@Param("username") String username);
}
