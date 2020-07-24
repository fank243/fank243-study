package com.fank243.springboot.core.dto.logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fank243.springboot.core.enums.AppLogType;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.enums.LogLevel;
import com.fank243.springboot.core.enums.SysLogType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * App日志
 * 
 * @author FanWeiJie
 * @date 2020-07-24 14:39:25
 */
@Data
@Entity
public class AppLogDTO {

    @Id
    private Long id;

    private String mobile;

    private String realname;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_level")
    private LogLevel logLevel;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_type")
    private SysLogType logType;

    @Column(name = "log_desc")
    private String logDesc;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "device_type")
    private DeviceType deviceType;

    @Column(name = "device_number")
    private String deviceNumber;

    @Column(name = "class_name")
    private String className;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "request_uri")
    private String requestUri;

    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "request_ip")
    private String requestIp;

    @Column(name = "request_ip_addr")
    private String requestIpAddr;

    @Column(name = "request_header")
    private String requestHeader;

    @Column(name = "request_body")
    private String requestBody;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "request_time")
    private Date requestTime;

    @Column(name = "response_status")
    private Integer responseStatus;

    @Column(name = "response_body")
    private String responseBody;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "response_time")
    private Date responseTime;

    @Column(name = "result_code")
    private Integer resultCode;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "gmt_created")
    private Date gmtCreated;

    public String getRequestBody() {
        if (StringUtils.isBlank(this.requestBody)) {
            return this.requestBody;
        }
        JSONObject object = JSONObject.parseObject(this.requestBody);
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteDateUseDateFormat);
    }

    public String getResponseBody() {
        if (StringUtils.isBlank(this.responseBody)) {
            return this.responseBody;
        }
        JSONObject object = JSONObject.parseObject(this.responseBody);
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteDateUseDateFormat);
    }

    @Transient
    public String logTypeCn;
    @Transient
    public String deviceTypeCn;
    @Transient
    private Long times;

    public String getLogTypeCn() {
        return this.logType.getDesc();
    }

    public String getDeviceTypeCn() {
        return this.deviceType.getDesc();
    }

    public Long getTimes() {
        if (this.requestTime == null || this.responseTime == null) {
            return 0L;
        }
        return this.responseTime.getTime() - this.requestTime.getTime();
    }
}
