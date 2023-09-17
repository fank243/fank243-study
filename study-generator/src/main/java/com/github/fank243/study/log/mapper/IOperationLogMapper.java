package com.github.fank243.study.log.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.log.entity.OperationLogEntity;

/**
 * 业务日志记录表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
public interface IOperationLogMapper extends BaseMapper<OperationLogEntity> {

     /**
     * 根据主键ID查询
     *
     * @param logId 主键
     * @return 业务日志记录表
     */
     OperationLogEntity findByLogId(Long logId);

     /**
     * 多条件查询
     *
     * @param operationLog 条件参数
     * @return 业务日志记录表
     */
     OperationLogEntity findByCondition(@Param("operationLog") OperationLogEntity operationLog);

     /**
     * 多条件查询
     *
     * @param operationLog 条件参数
     * @return 业务日志记录表
     */
     List<OperationLogEntity> findListByCondition(@Param("operationLog") OperationLogEntity operationLog);
}
