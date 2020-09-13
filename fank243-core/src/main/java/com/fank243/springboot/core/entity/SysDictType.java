package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 字典类别表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:45:52
 */
@Data
@Entity
@Table(name = "tb_sys_dict_type")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_dict_type", comment = "字典类别表")
public class SysDictType extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dict_type", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '字典类型'")
    private String dictType;

    @Column(name = "dict_name", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '字典名称'")
    private String dictName;

    @Column(name = "status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态（0正常 1停用）'")
    private Integer status;

    @Column(name = "remark", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注'")
    private String remark;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "create_time", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "update_time", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date updateTime;

    @Transient
    private String statusCn;

    public String getStatusCn() {
        return status == null || status == 0 ? "正常" : "禁用";
    }
}
