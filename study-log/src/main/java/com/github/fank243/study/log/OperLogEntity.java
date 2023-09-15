package com.github.fank243.study.log;

import java.io.Serializable;
import java.util.Date;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.keygen.KeyGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 操作日志表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@UseDataSource("support")
@Table("tb_operation_log")
public class OperLogEntity implements Serializable {

    /** 日志唯一ID */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String logId;

    /** 租户 */
    private String tenant;

    /** 保存的操作日志的类型 */
    private String logType;

    /** 日志绑定的业务标识 */
    private String bizNo;

    /** 日志的子类型 */
    private String subType;

    /** 操作人ID */
    private String operatorId;

    /** 日志内容 */
    private String content;

    /** 扩展信息 */
    private String extra;

    /** 日志操作时间 */
    private Date createTime;
}
