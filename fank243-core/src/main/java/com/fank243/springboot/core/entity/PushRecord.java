package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.DeviceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 推送消息发送记录表
 * 
 * @author FanWeiJie
 * @date 2019-11-02 15:44:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_record_push")
@org.hibernate.annotations.Table(appliesTo = "tb_record_push", comment = "推送通知发送记录表")
public class PushRecord extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Long userId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "device_type", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '设备类型(0：未知，1：安卓，2：苹果)'")
    private DeviceType deviceType;

    @Column(name = "device_token", columnDefinition = "VARCHAR(64) NOT NULL DEFAULT '' COMMENT '推送设备号'")
    private String deviceToken;

    @Column(name = "title", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '通知主题'")
    private String title;

    @Column(name = "content", columnDefinition = "LONGTEXT NULL COMMENT '通知内容'")
    private String content;

    @Column(name = "ip", columnDefinition = "VARCHAR(46) NOT NULL DEFAULT '' COMMENT 'IP地址'")
    private String ip;

    @Column(name = "ip_addr", columnDefinition = "VARCHAR(30) DEFAULT '' COMMENT 'IP归属地'")
    private String ipAddr;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;
}
