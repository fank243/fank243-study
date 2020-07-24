package com.fank243.springboot.app.service;

import com.fank243.springboot.app.repository.AppLogRepository;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.logger.AppLogDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * App 日志
 * 
 * @author FanWeiJie
 * @date 2020-07-24 22:53:31
 */
@Service
public class AppLogService {

    @Resource
    private AppLogRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo addRecord(AppLogDO appLog) {
        appLog = repository.save(appLog);
        if (appLog.getId() == null || appLog.getId() <= 0) {
            return ResultInfo.fail("添加记录失败");
        }
        return ResultInfo.ok();
    }
}
