package com.fank243.springboot.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统配置表
 * 
 * @author FanWeiJie
 * @date 2020-03-20 21:17:47
 */
@Data
@Entity
@Table(name = "tb_sys_config")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_config", comment = "系统配置表")
public class SysConfig implements Serializable {
    public SysConfig() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sys_key", unique = true, columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '键'")
    private String sysKey;

    @Column(name = "sys_value", columnDefinition = "VARCHAR(500) NOT NULL DEFAULT '' COMMENT '值'")
    private String sysValue;

    @Column(name = "description", columnDefinition = "VARCHAR(30) NULL DEFAULT '' COMMENT '描述'")
    private String description;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime gmtCreated;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_modified",
            columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期'")
    private LocalDateTime gmtModified = LocalDateTime.now();

}
