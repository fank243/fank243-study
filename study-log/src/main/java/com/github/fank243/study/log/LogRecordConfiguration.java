package com.github.fank243.study.log;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import com.mzt.logapi.starter.annotation.EnableLogRecord;

import cn.dev33.satoken.stp.StpUtil;

/**
 * 操作日志配置
 *
 * @author FanWeiJie
 * @since 2022-10-07 21:00:04
 */
@EnableLogRecord(tenant = "com.github.fank243.study")
@Configuration
public class LogRecordConfiguration {

    @Bean
    public IOperatorGetService operatorGetService() {
        return () -> Optional.of(StpUtil.getTokenInfo()).map(a -> new Operator((String)a.getLoginId()))
            .orElseThrow(() -> new IllegalArgumentException("user is null"));
    }
}
