package com.fank243.springboot.core.dto;

import com.fank243.springboot.core.enums.PermissionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限权限表
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:12:40
 */
@Data
@Entity
public class SysPermissionDTO implements Serializable {

    @Id
    private Long id;

    private String name;

    @Column(name = "pid_name")
    private String pidName;

    @Enumerated(EnumType.STRING)
    private PermissionType type;

    private String permission;

    private String uri;

    private Boolean external;

    private String icon;

    private Integer sort;

    private Boolean available;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Transient
    private String typeCn;

    public String getTypeCn() {
        return this.type.getDesc();
    }
}
