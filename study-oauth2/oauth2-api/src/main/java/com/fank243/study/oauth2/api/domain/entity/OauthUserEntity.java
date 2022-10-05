package com.fank243.study.oauth2.api.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.study.common.core.constants.DateConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
@TableName("tb_oauth_user")
public class OauthUserEntity {

    /** 用户ID */
    @TableId
    private String userId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    private String password;

    /** 状态(0：正常，1：禁用，2：登录锁定) */
    private Integer status;

    /** 登录累计错误次数 */
    private Integer loginErrCount;

    /** 登录锁定时间 */
    private Date loginLockTime;

    /** 最近登录时间 */
    private Date lastLoginTime;

    /** 最近登录IP */
    private String lastLoginIp;

    /** 是否已删除(0：未删除，1：已删除) */
    @TableLogic
    private Integer isDeleted;

    /** 创建时间 **/
    @JsonFormat(pattern = DateConstant.YY_MM_DD_HH_MM_SS)
    @TableField(fill = FieldFill.INSERT)
    private Date createdDate;

    /** 最近修改时间 **/
    @JsonFormat(pattern = DateConstant.YY_MM_DD_HH_MM_SS)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastModifiedDate;
}
