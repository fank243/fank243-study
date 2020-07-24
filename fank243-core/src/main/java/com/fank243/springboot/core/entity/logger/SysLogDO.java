package com.fank243.springboot.core.entity.logger;

import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.enums.LogLevel;
import com.fank243.springboot.core.enums.SysLogType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 后台日志
 * 
 * @author FanWeiJie
 * @date 2020-07-24 23:01:56
 */
@Data
@Entity
@Table(name = "tb_log_system")
@org.hibernate.annotations.Table(appliesTo = "tb_log_system", comment = "系统请求响应日志记录表")
public class SysLogDO extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id", columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '管理员ID'")
    private Long adminId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_level", columnDefinition = "VARCHAR(10) NOT NULL DEFAULT '' COMMENT '日志级别'")
    private LogLevel logLevel;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_type", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '日志类别'")
    private SysLogType logType;

    @Column(name = "log_desc", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '日志描述'")
    private String logDesc;

    @Column(name = "class_name", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '类名'")
    private String className;

    @Column(name = "method_name", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '方法名'")
    private String methodName;

    @Column(name = "session_id", columnDefinition = "VARCHAR(48) NOT NULL DEFAULT '' COMMENT 'Session ID'")
    private String sessionId;

    @Column(name = "request_id", columnDefinition = "VARCHAR(48) NOT NULL DEFAULT '' COMMENT 'Request ID'")
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

    @Column(name = "request_time", columnDefinition = "timestamp NULL COMMENT '请求时间'")
    private Date requestTime;

    @Column(name = "response_status", columnDefinition = "INT(3) NOT NULL DEFAULT 200 COMMENT '响应状态码'")
    private Integer responseStatus;

    @Column(name = "response_body", columnDefinition = "LONGTEXT NULL COMMENT '响应参数'")
    private String responseBody;

    @Column(name = "response_time", columnDefinition = "timestamp NULL COMMENT '响应时间'")
    private Date responseTime;

    @Column(name = "result_code", columnDefinition = "INT(4) NOT NULL DEFAULT 0 COMMENT 'Result Code'")
    private Integer resultCode;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

}
