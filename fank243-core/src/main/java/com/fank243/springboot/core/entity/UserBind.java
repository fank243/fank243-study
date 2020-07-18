package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.UserBindType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录绑定平台
 * 
 * @author FanWeiJie
 * @date 2020-03-24 17:13:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_user_bind")
@org.hibernate.annotations.Table(appliesTo = "tb_user_bind", comment = "用户登录绑定平台表")
public class UserBind extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Long userId;

    @Column(name = "open_id", unique = true, columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'OpenID'")
    private String openId;

    @Enumerated
    @Column(name = "type", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '开放平台(0：微信，1：QQ，2：微博，3：支付宝)'")
    private UserBindType type;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

}
