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

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.github.fank243.kong.core.annotation.CustomerIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员登录日志表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 16:26:01
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_sys_user_login_log")
public class SysUserLoginLogEntity implements Serializable {

    /** 主键ID */
    @Id
    @CustomerIdGenerator
    @Column(columnDefinition = "bigint(20) not null comment '主键ID'")
    private Long logId;

    /** 用户ID */
    @Column(columnDefinition = "bigint(20) not null comment '用户ID'")
    private Long userId;

    /** 登录时间 */
    @Column(columnDefinition = "datetime not null comment '登录时间'")
    private LocalDateTime loginTime;

    /** 登录IP */
    @Column(columnDefinition = "varchar(64) not null default '' comment '登录IP'")
    private String loginIp;

    /** 登录设备 */
    @Column(columnDefinition = "varchar(64) not null default '' comment '登录设备'")
    private String loginDevice;

}
