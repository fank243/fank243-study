package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.TemplateCode;
import com.fank243.springboot.core.enums.TemplateType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 模板消息
 *
 * @author FanWeiJie
 * @date 2019-11-04 12:00:03
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
@Table(name = "tb_template_notice")
@org.hibernate.annotations.Table(appliesTo = "tb_template_notice", comment = "通知模板表")
public class TemplateNotice extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type",
        columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '模板类型(0：站内信，1：短信，2：电子邮件，3：推送消息，99：其他)'")
    private TemplateType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '模板代码'")
    private TemplateCode code;

    @Column(name = "aliyun_code", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '阿里云短信代码'")
    private String aliyunCode;

    @Column(name = "name", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '模板名称'")
    private String name;

    @Column(name = "title", columnDefinition = "VARCHAR(30) NULL DEFAULT '' COMMENT '主题'")
    private String title;

    @Column(name = "content", columnDefinition = "LONGTEXT COMMENT '内容'")
    private String content;

    @Column(name = "available", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否可用(0：否，1：是)'")
    private Boolean available;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_modified",
        columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期'")
    private Date gmtModified;

    @Transient
    private String typeCn;

    public String getTypeCn() {
        return this.type.getDesc();
    }
}
