package com.fank243.study.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.gateway.dao.IReqRespLogDao;
import com.fank243.study.gateway.domain.ReqRespLogDTO;
import com.fank243.study.gateway.entity.ReqRespLogEntity;

import cn.hutool.core.bean.BeanUtil;

/**
 * 请求响应日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-06-28
 */
@Service
public class ReqRespLogService extends ServiceImpl<IReqRespLogDao, ReqRespLogEntity> {

    /**
     * 请求响应日志表_新增
     *
     * @param reqRespLog 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(ReqRespLogDTO reqRespLog) {
        ReqRespLogEntity reqRespLogEntity = BeanUtil.toBean(reqRespLog, ReqRespLogEntity.class);
        return save(reqRespLogEntity);
    }

}
