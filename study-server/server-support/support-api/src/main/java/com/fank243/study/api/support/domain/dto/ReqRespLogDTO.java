package com.fank243.study.api.support.domain.dto;

import com.fank243.study.common.domain.base.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
* 请求响应日志表
*
* @author FanWeiJie
* @since 2022-09-26 15:14:51
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class ReqRespLogDTO extends BaseDTO {


    /*** 操作人ID */
    private String userId;

    /*** 客户端ID */
    private String clientIp;

    /*** 请求方式 */
    private String reqMethod;

    /*** 请求地址 */
    private String reqUri;

    /*** 媒体类型 */
    private String contentType;

    /*** 请求头参数 */
    private String reqHeader;

    /*** 请求参数 */
    private String reqBody;

    /*** 响应参数 */
    private String respBody;

    /*** 请求时间 */
    private Date reqTime;

    /*** 响应时间 */
    private Date respTime;

    /*** 执行时间 */
    private Long executeTime;

    /*** traceId */
    private String traceId;

    /*** spanId */
    private String spanId;


}
