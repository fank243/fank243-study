package com.fank243.study.log.event;

import com.fank243.study.support.domain.dto.LogDTO;
import com.fank243.study.support.service.ILogService;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步监听日志事件
 * 
 * @author FanWeiJie
 * @since 2022-10-04 23:41:18
 */
@Slf4j
@RequiredArgsConstructor
public class LogListener {

    private final ILogService sysLogService;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        LogDTO sysLog = (LogDTO)event.getSource();
        sysLogService.add(sysLog);
    }

}
