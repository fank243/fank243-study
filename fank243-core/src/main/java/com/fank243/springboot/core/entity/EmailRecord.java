package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 短信发送记录
 *
 * @author FanWeiJie
 * @date 2019-11-02 15:44:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_record_email")
@org.hibernate.annotations.Table(appliesTo = "tb_record_email", comment = "电子邮件发送记录表")
public class EmailRecord extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Long userId;

    @Column(name = "email", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '电子邮箱地址'")
    private String email;

    @Column(name = "title", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '邮件主题'")
    private String title;

    @Column(name = "content", columnDefinition = "LONGTEXT COMMENT 'HTML内容'")
    private String content;

    @Column(name = "ip", columnDefinition = "VARCHAR(46) NOT NULL DEFAULT '' COMMENT 'IP地址'")
    private String ip;

    @Column(name = "ip_addr", columnDefinition = "VARCHAR(30) DEFAULT '' COMMENT 'IP归属地'")
    private String ipAddr;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;
}
