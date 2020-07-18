package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.enums.UserEventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户操作事件表
 * 
 * @author FanWeiJie
 * @date 2019-11-29 11:07:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_user_event")
@org.hibernate.annotations.Table(appliesTo = "tb_user_event", comment = "用户操作事件表")
public class UserEvent extends BaseEntity implements Serializable {

    public UserEvent() {}

    public UserEvent(Long userId, UserEventType type, DeviceType deviceType, String deviceNumber, String remark,
        String ip, String ipAddr) {
        this.userId = userId;
        this.type = type;
        this.deviceType = deviceType;
        this.deviceNumber = deviceNumber;
        this.remark = remark;
        this.ip = ip;
        this.ipAddr = ipAddr;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Long userId;

    @Enumerated
    @Column(name = "type", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '事件类型'")
    private UserEventType type;

    @Enumerated
    @Column(name = "device_type", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '设备类型(0：未知，1：安卓，2：苹果)'")
    private DeviceType deviceType;

    @Column(name = "device_number", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT '设备唯一标识符'")
    private String deviceNumber;

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
