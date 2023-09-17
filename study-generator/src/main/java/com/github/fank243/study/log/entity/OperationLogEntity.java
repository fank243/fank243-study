package com.github.fank243.study.log.entity;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 业务日志记录表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_operation_log", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class OperationLogEntity extends BaseEntity implements Serializable {

    /** 日志ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long logId;

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

}
