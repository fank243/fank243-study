package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.enums.Gender;
import com.fank243.springboot.core.enums.UserSource;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * 
 * @author FanWeiJie
 * @date 2020-03-24 16:48:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_user_info")
@org.hibernate.annotations.Table(appliesTo = "tb_user_info", comment = "用户信息表")
public class UserInfo extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Long userId;

    @Column(name = "mobile", unique = true, columnDefinition = "VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号码'")
    private String mobile;

    @Column(name = "realname", columnDefinition = "VARCHAR(10) NULL DEFAULT '' COMMENT '姓名'")
    private String realname;

    @Column(name = "id_card", columnDefinition = "VARCHAR(18) NULL DEFAULT '' COMMENT '身份证号码'")
    private String idCard;

    @Column(name = "is_verify_realname",
        columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已实名验证(0：未验证，1：已验证)'")
    private Boolean isVerifyRealname;

    @Column(name = "email", columnDefinition = "VARCHAR(100) NULL DEFAULT '' COMMENT '电子邮件'")
    private String email;

    @Column(name = "is_verify_email", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '邮箱是否已验证(0：未验证，1：已验证)'")
    private Boolean isVerifyEmail;

    @Enumerated
    @Column(name = "gender", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0：保密，1：男，2：女'")
    private Gender gender;

    @Column(name = "wx_number", columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '微信号'")
    private String wxNumber;

    @Column(name = "alipay_number", columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '支付宝账号'")
    private String alipayNumber;

    @Column(name = "qq", columnDefinition = "VARCHAR(11) NULL DEFAULT '' COMMENT 'QQ号码'")
    private String qq;

    @Column(name = "weibo", columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '微博账号'")
    private String weibo;

    @Enumerated
    @Column(name = "source",
        columnDefinition = "TINYINT(2) NOT NULL DEFAULT 0 COMMENT '注册来源(0：未知，1：安卓，2：苹果，3：微信，99：其他)'")
    private UserSource source;

    @Enumerated
    @Column(name = "device_type",
        columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '当前使用设备类型(0：未知，1：安卓，2：苹果，3：微信)'")
    private DeviceType deviceType;

    @Column(name = "ios_device_number", columnDefinition = "VARCHAR(50) NULL DEFAULT '' COMMENT 'IOS设备号'")
    private String iosDeviceNumber;

    @Column(name = "android_device_number", columnDefinition = "VARCHAR(50) NULL DEFAULT '' COMMENT 'Android设备号'")
    private String androidDeviceNumber;

    @Column(name = "device_token", columnDefinition = "VARCHAR(64) NULL DEFAULT '' COMMENT '设备识别码(推送Api提供)'")
    private String deviceToken;

    @Column(name = "addr", columnDefinition = "VARCHAR(200) NULL DEFAULT '' COMMENT '当前住址'")
    private String addr;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_modified",
        columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期'")
    private Date gmtModified;
}
