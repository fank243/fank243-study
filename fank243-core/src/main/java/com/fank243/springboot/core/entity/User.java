package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * 
 * @author FanWeiJie
 * @date 2020-03-24 16:47:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_user")
@org.hibernate.annotations.Table(appliesTo = "tb_user", comment = "用户表")
public class User extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户名'")
    private String username;

    @Column(name = "nickname", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '昵称'")
    private String nickname;

    @Column(name = "mobile", unique = true, columnDefinition = "VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号码'")
    private String mobile;

    @Column(name = "photo", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头像地址'")
    private String photo = IConsts.DEFAULT_PHOTO;

    @Column(name = "password", columnDefinition = "VARCHAR(32) NOT NULL DEFAULT '' COMMENT '登录密码'")
    private String password;

    @Column(name = "salt", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '密码加盐'")
    private String salt;

    @Enumerated
    @Column(name = "status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态(0：正常，1：禁用，1：登录锁定)'")
    private UserStatus status;

    @Column(name = "login_err_count", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '连续登录错误次数'")
    private Integer loginErrCount;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "login_lock_time", columnDefinition = "TIMESTAMP NULL COMMENT '登录锁定时间'")
    private Date loginLockTime;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "last_login_time", columnDefinition = "TIMESTAMP NULL COMMENT '最近登录时间'")
    private Date lastLoginTime;

    @Column(name = "last_login_ip", columnDefinition = "VARCHAR(46) NULL DEFAULT '' COMMENT '最近登录IP'")
    private String lastLoginIp;

    @Column(name = "last_login_ip_addr", columnDefinition = "VARCHAR(30) NULL DEFAULT '' COMMENT '最近登录IP归属地'")
    private String lastLoginIpAddr;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已删除(0：否，1：是)'")
    private Boolean isDeleted;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "deleted_time", columnDefinition = "timestamp NULL COMMENT '删除时间'")
    private Date deletedTime;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_modified",
        columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期'")
    private Date gmtModified = new Date();
}
