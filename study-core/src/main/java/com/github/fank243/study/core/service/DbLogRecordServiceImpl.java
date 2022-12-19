// package com.github.fank243.study.core.service;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.annotation.Resource;
//
// import org.springframework.stereotype.Service;
//
// import com.github.fank243.study.core.domain.dto.OperLogDTO;
// import com.mzt.logapi.beans.LogRecord;
// import com.mzt.logapi.service.ILogRecordService;
//
// import cn.hutool.core.bean.BeanUtil;
// import cn.hutool.core.thread.ThreadUtil;
// import cn.hutool.json.JSONUtil;
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * 操作日志入库配置
// *
// * @author FanWeiJie
// * @since 2022-10-07 21:04:59
// */
// @Slf4j
// @Service
// public class DbLogRecordServiceImpl implements ILogRecordService {
//
// @Resource
// private IOperLogService operLogService;
//
// @Override
// public void record(LogRecord logRecord) {
// if (log.isDebugEnabled()) {
// log.debug("操作日志拦截入库：{}", JSONUtil.toJsonStr(logRecord));
// }
//
// ThreadUtil.execute(() -> {
// OperLogDTO operLogDTO = BeanUtil.copyProperties(logRecord, OperLogDTO.class);
// operLogDTO.setLogType(logRecord.getType());
// operLogDTO.setOperatorId(logRecord.getOperator());
// operLogDTO.setContent(logRecord.getAction());
//
// operLogService.add(operLogDTO);
// });
//
// }
//
// @Override
// public List<LogRecord> queryLog(String bizNo, String type) {
// return new ArrayList<>(0);
// }
//
// @Override
// public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
// return new ArrayList<>(0);
// }
//
// }