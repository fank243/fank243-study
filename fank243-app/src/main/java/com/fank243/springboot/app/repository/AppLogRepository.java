package com.fank243.springboot.app.repository;

import com.fank243.springboot.core.entity.logger.AppLogDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * App 日志
 * 
 * @author FanWeiJie
 * @date 2020-07-24 22:54:04
 */
@Repository
public interface AppLogRepository extends JpaRepository<AppLogDO, Long> {}
