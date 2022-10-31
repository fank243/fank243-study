package com.github.fank243.study.support.domain.dto;

import java.util.Date;

import com.github.fank243.study.core.base.BaseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求响应日志表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Schema(description = "操作日志请求实体")
@Data
@EqualsAndHashCode(callSuper = true)
public class OperLogDTO extends BaseDTO {

    /** 日志唯一ID */
    @Schema(description = "操作日志ID")
    private String logId;

    /** 租户 */
    @Schema(description = "租户")
    private String tenant;

    /** 保存的操作日志的类型 */
    @Schema(description = "保存的操作日志的类型")
    private String logType;

    /** 日志绑定的业务标识 */
    @Schema(description = "日志绑定的业务标识")
    private String bizNo;

    /** 日志的子类型 */
    @Schema(description = "日志的子类型")
    private String subType;

    /** 方法执行时间（单位：毫秒） */
    @Schema(description = "方法执行时间", hidden = true)
    private Long executionTime;

    /** 操作人ID */
    @Schema(description = "操作人ID")
    private String operatorId;

    /** 日志内容 */
    @Schema(description = "日志内容")
    private String content;

    /** 扩展信息 */
    @Schema(description = "扩展信息")
    private String extra;

    /** 日志操作时间 */
    @Schema(description = "日志操作时间", hidden = true)
    private Date createTime;
}
