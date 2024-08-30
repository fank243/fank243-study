package com.github.fank243.kong.log.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 业务日志记录表 实体
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:11
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OperationLogEntity extends BaseDTO  {

    /** 日志ID */
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
