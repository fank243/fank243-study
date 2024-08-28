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

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.fank243.kong.system.domain.entity.SysUserEntity;

/**
 * 系统管理员表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Repository
public interface ISysUserRepository extends JpaRepository<SysUserEntity, Long> {

    List<SysUserEntity> findAllByIdIn(Collection<Long> ids);

    SysUserEntity findByOpenId(String openId);

}
