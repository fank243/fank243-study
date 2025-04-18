/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.log;

import java.util.List;

import org.springframework.stereotype.Service;

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
public class DbLogRecordServiceImpl implements ILogRecordService {

//    private final IOperLogRepository operLogRepository = new DbLogRecordServiceImpl();

    // public DbLogRecordServiceImpl(IOperLogRepository operLogRepository) {
    // this.operLogRepository = operLogRepository;
    // }

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

//            operLogRepository.save(operLog);
        });
    }

    @Deprecated
    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return null;
    }

    @Deprecated
    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return null;
    }

}