package com.github.fank243.kong.oauth2.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.ColumnMask;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Oauth2用户表 实体
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:10
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OauthUserEntity extends BaseDTO  {

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    private String username;

    /** 手机号码 */
    private String mobile;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    private String password;

    /** 用户状态(0：正常，1：登录锁定，2：已禁用) */
    private Integer status;

    /** 连续登录错误次数 */
    private Integer loginErrCount;

    /** 登录锁定时间 */
    private LocalDateTime loginLockTime;

    /** 最后登录时间 */
    private LocalDateTime lastLoginTime;

    /** 最后登录IP */
    private String lastLoginIp;

    /** 是否已删除(0：否，1：是) */
    private Boolean isDeleted;

}