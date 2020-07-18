package com.fank243.springboot.core.entity.ipaddr;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * IP地址库
 * 
 * @author FanWeiJie
 * @date 2020-03-07 11:42:58
 */
@Data
@Entity
@Table(name = "tb_3rd_pconline")
@org.hibernate.annotations.Table(appliesTo = "tb_3rd_pconline", comment = "太平洋IP地址库")
public class IpAddr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'IP地址'")
    private String ip;

    @Column(name = "addr", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT '详细地址(市级，国外为0)'")
    private String addr;

    @Column(name = "pro_code", columnDefinition = "INT(6) NOT NULL DEFAULT 0 COMMENT '行政区划代码(省级，国外为999999)'")
    private Integer proCode;

    @Column(name = "pro", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '行政区划名称(省级，国外为0)'")
    private String pro;

    @Column(name = "city_code", columnDefinition = "INT(6) NOT NULL DEFAULT 0 COMMENT '行政区划代码(市级，国外为0)'")
    private Integer cityCode;

    @Column(name = "city", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '行政区划名称(市级，国外为0)'")
    private String city;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime gmtCreated;
}
