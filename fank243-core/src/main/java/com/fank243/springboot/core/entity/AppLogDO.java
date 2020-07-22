package com.fank243.springboot.core.entity;

import com.fank243.springboot.core.enums.AppLogType;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.enums.LogLevel;
import com.fank243.springboot.core.enums.SysLogType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_log_app")
@org.hibernate.annotations.Table(appliesTo = "tb_log_app", comment = "App请求响应日志记录表")
public class AppLogDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Long userId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_level", columnDefinition = "VARCHAR(10) NOT NULL DEFAULT '' COMMENT '日志级别'")
    private LogLevel logLevel;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_type", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '日志类别'")
    private AppLogType logType;

    @Column(name = "log_desc", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '日志描述'")
    private String logDesc;

    @Column(name = "class_name", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '类名'")
    private String className;

    @Column(name = "method_name", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '方法名'")
    private String methodName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "device_type", columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '设备类型'")
    private DeviceType deviceType;

    @Column(name = "device_number", columnDefinition = "TINYINT(48) NULL DEFAULT '' COMMENT '设备号'")
    private String deviceNumber;

    @Column(name = "request_id", columnDefinition = "VARCHAR(48) NOT NULL DEFAULT '' COMMENT '请求ID'")
    private String requestId;

    @Column(name = "request_uri", columnDefinition = "VARCHAR(48) NOT NULL DEFAULT '' COMMENT '请求URI'")
    private String requestUri;

    @Column(name = "request_method", columnDefinition = "VARCHAR(48) NOT NULL DEFAULT '' COMMENT '请求URI'")
    private String requestMethod;

    @Column(name = "request_ip", columnDefinition = "VARCHAR(46) NULL DEFAULT '' COMMENT '请求IP'")
    private String requestIp;

    @Column(name = "request_ip_addr", columnDefinition = "VARCHAR(46) NULL DEFAULT '' COMMENT '请求IP归属地'")
    private String requestIpAddr;

    @Column(name = "request_header", columnDefinition = "LONGTEXT NULL COMMENT '请求头参数'")
    private String requestHeader;

    @Column(name = "request_body", columnDefinition = "LONGTEXT NULL COMMENT '请求体参数'")
    private String requestBody;

    @Column(name = "response_status", columnDefinition = "INT(3) NOT NULL DEFAULT 200 COMMENT '响应状态码'")
    private Integer responseStatus;

    @Column(name = "response_body", columnDefinition = "LONGTEXT NULL COMMENT '响应参数'")
    private String responseBody;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

}
