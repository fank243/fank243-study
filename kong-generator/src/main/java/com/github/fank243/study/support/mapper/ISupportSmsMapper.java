package com.github.fank243.kong.support.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.support.dto.SupportSmsDTO;

/**
* 短信发送记录表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:11
*/
public interface ISupportSmsMapper extends BaseMapper<SupportSmsEntity> {

    /**
    * 根据主键ID查询
    *
    * @param smsId 主键
    * @return 短信发送记录表
    */
    SupportSmsDTO findBySmsId(Long smsId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 短信发送记录表
    */
    SupportSmsDTO findByCondition(@Param("params") SupportSmsDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 短信发送记录表
    */
    List<SupportSmsDTO> findListByCondition(@Param("params") SupportSmsDTO params);
}
