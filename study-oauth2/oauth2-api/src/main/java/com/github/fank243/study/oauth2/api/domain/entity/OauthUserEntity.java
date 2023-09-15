package com.github.fank243.study.oauth2.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fank243.study.core.constants.DateConstants;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import jakarta.validation.constraints.NotBlank;
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
@Table("tb_oauth_user")
public class OauthUserEntity implements Serializable {

    /** 用户ID */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String userId;

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[username]不能为空", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    @NotBlank(message = "请填写登录密码", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[password]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
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
    @Column(onInsertValue = "now()")
    private Date createdDate;

    /** 最近修改时间 **/
    @JsonFormat(pattern = DateConstants.YYYY_MM_DD_HH_MM_SS)
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private Date lastModifiedDate;
}
