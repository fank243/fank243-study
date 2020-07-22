package com.fank243.springboot.admin.repository;

import com.fank243.springboot.core.entity.SysLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogRepository extends JpaRepository<SysLogDO, Long> {}
