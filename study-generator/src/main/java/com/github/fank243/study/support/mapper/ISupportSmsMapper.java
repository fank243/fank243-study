package com.github.fank243.study.support.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.support.entity.SupportSmsEntity;

/**
 * 短信发送记录表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
public interface ISupportSmsMapper extends BaseMapper<SupportSmsEntity> {

     /**
     * 根据主键ID查询
     *
     * @param smsId 主键
     * @return 短信发送记录表
     */
     SupportSmsEntity findBySmsId(Long smsId);

     /**
     * 多条件查询
     *
     * @param supportSms 条件参数
     * @return 短信发送记录表
     */
     SupportSmsEntity findByCondition(@Param("supportSms") SupportSmsEntity supportSms);

     /**
     * 多条件查询
     *
     * @param supportSms 条件参数
     * @return 短信发送记录表
     */
     List<SupportSmsEntity> findListByCondition(@Param("supportSms") SupportSmsEntity supportSms);
}
