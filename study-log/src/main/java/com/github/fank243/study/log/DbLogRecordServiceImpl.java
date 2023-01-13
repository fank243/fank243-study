package com.github.fank243.study.log;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.study.log.mapper.IOperLogMapper;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 操作日志入库配置
 *
 * @author FanWeiJie
 * @since 2022-10-07 21:04:59
 */
@Slf4j
@Service
public class DbLogRecordServiceImpl extends ServiceImpl<IOperLogMapper, OperLogEntity> implements ILogRecordService {

    @Override
    public void record(LogRecord logRecord) {
        if (log.isDebugEnabled()) {
            log.debug("操作日志拦截入库：{}", JSONUtil.toJsonStr(logRecord));
        }

        ThreadUtil.execute(() -> {
            OperLogEntity operLog = BeanUtil.copyProperties(logRecord, OperLogEntity.class);
            operLog.setLogType(logRecord.getType());
            operLog.setOperatorId(logRecord.getOperator());
            operLog.setContent(logRecord.getAction());

            save(operLog);
        });

    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return new ArrayList<>(0);
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return new ArrayList<>(0);
    }

}