package com.fank243.study.support.domain.dto;

import java.util.Date;

import com.fank243.study.common.core.base.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求响应日志表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperLogDTO extends BaseDTO {

    /** 日志唯一ID */
    private String logId;

    /** 租户 */
    private String tenant;
    
    /** 保存的操作日志的类型 */
    private String logType;

    /** 日志绑定的业务标识 */
    private String bizNo;

    /** 日志的子类型 */
    private String subType;

    /** 方法执行时间（单位：毫秒） */
    private Long executionTime;

    /** 操作人ID */
    private String operatorId;

    /** 日志内容 */
    private String content;

    /** 扩展信息 */
    private String extra;

    /** 日志操作时间 */
    private Date createTime;
}
