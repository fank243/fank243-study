package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色表
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:12:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_sys_role")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_role", comment = "角色表")
public class SysRole extends BaseEntity implements Serializable {

    public SysRole() {}

    /** 添加 **/
    public SysRole(String name, String description) {
        this.name = name;
        this.description = description;
        this.available = Boolean.TRUE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '角色名称'")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(30) NULL DEFAULT '' COMMENT '角色描述'")
    private String description;

    @Column(name = "available", columnDefinition = "TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '是否可用(0:否，1：是)'")
    private Boolean available;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_modified",
        columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期'")
    private Date gmtModified;

    /** 角色与权限的关联关系 **/
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_role_permission", joinColumns = {@JoinColumn(name = "role_id")},
        inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<SysPermission> permissions = new HashSet<>();

}
