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

package com.github.fank243.kong.oauth2.domain;

import java.io.Serializable;
import java.util.Date;

import com.github.fank243.kong.core.annotation.CustomerIdGenerator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fank243.kong.core.configuration.YitIdGenerator;
import com.github.fank243.kong.core.constants.DateConstants;
import com.github.fank243.kong.oauth2.api.convert.EnumConvert;
import com.github.fank243.kong.oauth2.api.domain.enums.Oauth2UserStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@SQLDelete(sql = "update tb_oauth_user set is_deleted = true where user_id = ?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_oauth_user")
public class OauthUserEntity implements Serializable {

    /** 用户ID */
    @Id
    @CustomerIdGenerator
    private Long userId;

    /** 用户名 */
    private String username;

    /** 手机号码 */
    private String mobile;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    private String password;

    /** 状态(0：正常，1：禁用，2：登录锁定) */
    @Convert(converter = EnumConvert.Oauth2UserStatusConverter.class)
    private Oauth2UserStatusEnum status = Oauth2UserStatusEnum.NORMAL;

    /** 登录累计错误次数 */
    @Column(insertable = false)
    private Integer loginErrCount;

    /** 登录锁定时间 */
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    private Date loginLockTime;

    /** 最近登录时间 */
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    @Column(insertable = false)
    private Date lastLoginTime;

    /** 最近登录IP */
    @Column(insertable = false)
    private String lastLoginIp;

    /** 是否已删除(0：未删除，1：已删除) */
    private Boolean isDeleted = false;

    /** 创建时间 **/
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    /** 最近修改时间 **/
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    @LastModifiedDate
    @Column(nullable = false)
    private Date lastModifiedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public OauthAccessTokenEntity oauthAccessToken;
}
