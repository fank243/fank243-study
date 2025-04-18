/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.system.domain.entity;

import com.github.fank243.kong.system.domain.base.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.github.fank243.kong.system.convert.EnumConvert;
import com.github.fank243.kong.system.domain.enums.PermTypeEnum;
import com.github.fank243.kong.system.domain.enums.StatusEnum;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统权限表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 16:26:08
 */
@SQLDelete(sql = "update tb_sys_perm set is_deleted = true where perm_id = ?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_sys_perm")
public class SysPermEntity extends BaseEntity {

    /** 权限父ID */
    private Long permPid;

    /** 权限代码 */
    private String permCode;

    /** 权限路径 */
    private String permUri;

    /** 权限名称 */
    private String permName;

    /** 权限描述 */
    private String permDesc;

    /** 权限类型(0：目录，1：菜单，2：接口) */
    @Convert(converter = EnumConvert.PermTypeConverter.class)
    private PermTypeEnum permType;

    /** 权限状态(0：正常，1：禁用) */
    @Convert(converter = EnumConvert.StatusConverter.class)
    private StatusEnum status;

    /** 是否外部链接(0：否，1：是) */
    private Boolean isExternal;
}
