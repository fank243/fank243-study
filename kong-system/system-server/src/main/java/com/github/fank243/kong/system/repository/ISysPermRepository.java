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

package com.github.fank243.kong.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.fank243.kong.system.domain.entity.SysPermEntity;
import com.github.fank243.kong.system.domain.enums.PermTypeEnum;
import com.github.fank243.kong.system.domain.enums.StatusEnum;

/**
 * 系统权限表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Repository
public interface ISysPermRepository extends JpaRepository<SysPermEntity, Long> {

    /**
     * 根据userId查询权限集合
     *
     * @param userId 管理员ID
     * @return 权限集合
     */
    @Query(nativeQuery = true,
        value = """
            SELECT perm.* FROM `tb_sys_perm` perm LEFT JOIN tb_sys_role_perm rp ON rp.perm_id=perm.perm_id LEFT JOIN tb_sys_user_role role ON role.role_id=rp.role_id WHERE perm.status = 0 and role.user_id= ?
            """)
    List<SysPermEntity> findByUserId(String userId);

    /**
     * 根据权限类型查询
     *
     * @param permTypes 权限类型
     * @param status 状态
     * @return 权限集合
     */
    List<SysPermEntity> findAllByPermTypeInAndStatusEquals(List<PermTypeEnum> permTypes, StatusEnum status);

    SysPermEntity findByPermCode(String permCode);

    List<SysPermEntity> findAllByIdIn(List<Long> ids);
}
