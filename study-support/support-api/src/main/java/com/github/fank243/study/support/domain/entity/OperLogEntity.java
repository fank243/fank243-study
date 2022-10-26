package com.github.fank243.study.support.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 操作日志表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
@TableName("tb_operation_log")
public class OperLogEntity implements Serializable {

    /** 日志唯一ID */
    @TableId
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
