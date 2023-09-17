package com.github.fank243.study.oauth2.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fank243.study.core.constants.DateConstants;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.ColumnMask;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

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
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_oauth_user", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class OauthUserEntity implements Serializable {

    /** 用户ID */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String userId;

    /** 用户名 */
    private String username;

    /** 手机号码 */
    @ColumnMask("mobile")
    private String mobile;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    // @ColumnMask(Masks.PASSWORD)
    private String password;

    /** 状态(0：正常，1：禁用，2：登录锁定) */
    private Integer status;

    /** 登录累计错误次数 */
    private Integer loginErrCount;

    /** 登录锁定时间 */
    @JSONField(format = DateConstants.YYYY_MM_DD_HH_MM_SS)
    private Date loginLockTime;

    /** 最近登录时间 */
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    private Date lastLoginTime;

    /** 最近登录IP */
    private String lastLoginIp;

    /** 是否已删除(0：未删除，1：已删除) */
    @Column(isLogicDelete = true)
    private Integer isDeleted;

    /** 创建时间 **/
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    private Date createdDate;

    /** 最近修改时间 **/
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    private Date lastModifiedDate;
}
