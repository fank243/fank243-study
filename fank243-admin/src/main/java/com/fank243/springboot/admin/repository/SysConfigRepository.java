package com.fank243.springboot.admin.repository;

import com.fank243.springboot.core.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 系统配置持久层
 * 
 * @author FanWeiJie
 * @date 2020-03-08 17:13:52
 */
@Repository
public interface SysConfigRepository extends JpaRepository<SysConfig, Long> {
    /**
     * 根据sysKey查找
     * 
     * @param sysKey 键
     * @return SysConfig
     */
    SysConfig findBySysKey(String sysKey);
}
