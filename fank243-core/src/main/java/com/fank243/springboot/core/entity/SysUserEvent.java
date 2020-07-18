package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员操作事件表
 * 
 * @author FanWeiJie
 * @date 2019-11-29 11:07:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_sys_user_event")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_user_event", comment = "管理员操作事件表")
public class SysUserEvent extends BaseEntity implements Serializable {

    public SysUserEvent() {}

    public SysUserEvent(Long sysUserId, SysUserEventType type, String remark, String ip, String ipAddr) {
        this.sysUserId = sysUserId;
        this.type = type;
        this.remark = remark;
        this.ip = ip;
        this.ipAddr = ipAddr;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sys_user_id", columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '管理员ID'")
    private Long sysUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '事件类型'")
    private SysUserEventType type;

    @Column(name = "remark", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT '备注'")
    private String remark;

    @Column(name = "ip", columnDefinition = "VARCHAR(46) NOT NULL DEFAULT '' COMMENT '操作IP地址'")
    private String ip;

    @Column(name = "ip_addr", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT '操作IP地址归属地'")
    private String ipAddr;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime gmtCreated;

}
