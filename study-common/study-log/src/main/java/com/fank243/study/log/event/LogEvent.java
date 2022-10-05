package com.fank243.study.log.event;

import org.springframework.context.ApplicationEvent;

import com.fank243.study.support.domain.dto.LogDTO;

/**
 * 系统日志事件
 * 
 * @author FanWeiJie
 * @since 2022-10-04 23:42:06
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(LogDTO source) {
        super(source);
    }

}
