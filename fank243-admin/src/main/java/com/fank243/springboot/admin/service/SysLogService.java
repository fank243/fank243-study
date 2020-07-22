package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.repository.SysLogRepository;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.SysLogDO;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 业务日志
 * 
 * @author FanWeiJie
 * @date 2020-07-23 00:10:00
 */
@Service
public class SysLogService {

    @Resource
    private SysLogRepository repository;
    @Resource
    private IpAddrService ipAddrService;

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo addRecord(SysLogDO sysLog) {
        if (StringUtils.isNotBlank(sysLog.getRequestIp())) {
            IpAddr ipAddr = ipAddrService.getIpAddr(sysLog.getRequestIp());
            sysLog.setRequestIp(ipAddr.getIp());
            sysLog.setRequestIpAddr(ipAddr.getAddr());
        }
        repository.save(sysLog);
        if (sysLog.getId() == null || sysLog.getId() <= 0) {
            return ResultInfo.fail("添加记录失败");
        }
        return ResultInfo.ok();
    }
}
