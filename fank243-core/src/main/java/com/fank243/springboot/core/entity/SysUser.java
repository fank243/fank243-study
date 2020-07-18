package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.SysUserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 管理员表
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:12:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Entity
@Table(name = "tb_sys_user")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_user", comment = "管理员表")
public class SysUser extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户名'")
    private String username;

    @Column(name = "realname", unique = true, columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '姓名'")
    private String realname;

    @Column(name = "mobile", unique = true, columnDefinition = "VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号码'")
    private String mobile;

    @Column(name = "wx_number", columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '微信号码'")
    private String wxNumber;

    @Column(name = "email", columnDefinition = "VARCHAR(100) NULL DEFAULT '' COMMENT '电子邮箱'")
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(32) NOT NULL DEFAULT '' COMMENT '登录密码'")
    private String password;

    @Column(name = "salt", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '密码加盐'")
    private String salt;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态(0:正常，1：禁用，2：登录锁定)'")
    private SysUserStatus status;

    @Column(name = "login_err_count", columnDefinition = "TINYINT(2) NOT NULL DEFAULT 0 COMMENT '登录错误次数'")
    private Integer loginErrCount = 0;

    @JsonFormat(pattern = "yy/MM/dd HH:mm")
    @Column(name = "login_lock_time", columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '登录锁定时间'")
    private Date loginLockTime;

    @Column(name = "last_login_ip", columnDefinition = "varchar(46) DEFAULT '' COMMENT '最后登录IP'")
    private String lastLoginIp;

    @Column(name = "last_login_ip_addr", columnDefinition = "varchar(30) DEFAULT '' COMMENT '最后登录IP归属地'")
    private String lastLoginIpAddr;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "last_login_time", columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '最后登录IP'")
    private Date lastLoginTime;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已删除(0:未删除，1：已删除)'")
    private Boolean isDeleted = Boolean.FALSE;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "deleted_time", columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '删除时间'")
    private Date deletedTime;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

    @JsonFormat(pattern = "yy/MM/dd HH:mm")
    @Column(name = "gmt_modified",
        columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期'")
    private Date gmtModified;

    /** 管理员与角色的关联关系 **/
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_sys_user_role",
        joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRole> roles = new HashSet<>();

    @Transient
    private String statusCn;

    public String getStatusCn() {
        return this.status.getDesc();
    }
}
