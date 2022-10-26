package com.github.fank243.study.support.domain.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 请求响应日志表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
public class ReqRespLogDTO implements Serializable {

    /*** 日志类型 */
    private String logType;

    /** 操作人ID */
    private String userId;

    /** 客户端ID */
    private String clientIp;

    /** 请求方式 */
    private String reqMethod;

    /** 请求地址 */
    private String reqUri;

    /** 媒体类型 */
    private String contentType;

    /** 请求头参数 */
    private String reqHeader;

    /** 请求参数 */
    private String reqBody;

    /** 响应参数 */
    private String respBody;

    /** 请求时间 */
    private Date reqTime;

    /** 响应时间 */
    private Date respTime;

    /** 执行时间 */
    private Long executeTime;

    /** traceId */
    private String traceId;

    /** spanId */
    private String spanId;

    public Long getExecuteTime() {
        return this.respTime.getTime() - this.reqTime.getTime();
    }
}
