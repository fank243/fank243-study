package com.github.fank243.kong.log.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.log.dto.OperationLogDTO;

/**
* 业务日志记录表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:11
*/
public interface IOperationLogMapper extends BaseMapper<OperationLogEntity> {

    /**
    * 根据主键ID查询
    *
    * @param logId 主键
    * @return 业务日志记录表
    */
    OperationLogDTO findByLogId(Long logId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 业务日志记录表
    */
    OperationLogDTO findByCondition(@Param("params") OperationLogDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 业务日志记录表
    */
    List<OperationLogDTO> findListByCondition(@Param("params") OperationLogDTO params);
}
