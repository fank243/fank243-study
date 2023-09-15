package com.github.fank243.study.core.model.mf;

import org.springframework.context.annotation.Configuration;

import com.mybatisflex.core.audit.AuditManager;

import lombok.extern.slf4j.Slf4j;

/**
 * Mybatis-flex 配置
 * 
 * @author FanWeiJie
 * @date 2023/09/15 20:32
 */
@Slf4j
@Configuration
public class MyBatisFlexConfiguration {

    public MyBatisFlexConfiguration() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> log.info("MyBatis Flex 审计日志：{}", auditMessage));
    }
}
