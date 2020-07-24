package com.fank243.springboot.admin.repository.logger;

import com.fank243.springboot.core.entity.logger.SysLogDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * App 日志
 * 
 * @author FanWeiJie
 * @date 2020-07-24 23:04:57
 */
@Repository
public interface AppLogRepository extends JpaRepository<SysLogDO, Long> {}
