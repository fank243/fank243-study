package com.fank243.springboot.admin.service.logger;

import com.fank243.springboot.admin.repository.logger.SysLogRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.SysLog;
import com.fank243.springboot.core.dto.SysUserEventDTO;
import com.fank243.springboot.core.dto.logger.SysLogDTO;
import com.fank243.springboot.core.entity.logger.SysLogDO;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务日志
 * 
 * @author FanWeiJie
 * @date 2020-07-23 00:10:00
 */
@Service
public class SysLogService extends BaseService<SysLogDO> {

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

    public PageBean<SysLogDTO> pageOfLog(PageInfo pageInfo, String keyword, String logType, String logLevel,
        String logDesc, String requestUri, String sessionId, String requestId, Integer responseStatus,
        Integer resultCode, String beginTime, String endTime) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT COUNT(*) FROM tb_log_system a LEFT JOIN tb_sys_user b ON a.admin_id=b.id WHERE 1=1");
        querySql.append(
            "SELECT a.id,b.realname,b.mobile,log_level,log_type,log_desc,class_name,method_name,session_id,request_id,request_uri,request_method,request_ip,request_ip_addr,request_header,request_body,response_status,response_body,request_time,response_time,result_code,a.gmt_created  FROM tb_log_system a LEFT JOIN tb_sys_user b ON a.admin_id=b.id where 1=1");
        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(keyword)) {
            querySql.append(" AND (b.realname like :keyword or b.mobile like :keyword))");
            countSql.append(" AND (b.realname like :keyword or b.mobile like :keyword))");
            conditionArgs.put("keyword", "%" + keyword + "%");
        }
        if (StringUtils.isNotBlank(logType)) {
            querySql.append(" AND log_type =:logType");
            countSql.append(" AND log_type =:logType");
            conditionArgs.put("logType", logType);
        }
        if (StringUtils.isNotBlank(logLevel)) {
            querySql.append(" AND log_level =:level");
            countSql.append(" AND log_level =:level");
            conditionArgs.put("logLevel", logLevel);
        }
        if (StringUtils.isNotBlank(logDesc)) {
            querySql.append(" AND log_desc =:logDesc");
            countSql.append(" AND log_desc =:logDesc");
            conditionArgs.put("logDesc", logDesc);
        }
        if (StringUtils.isNotBlank(requestUri)) {
            querySql.append(" AND request_uri like :requestUri");
            countSql.append(" AND request_uri like :requestUri");
            conditionArgs.put("requestUri", requestUri + "%");
        }
        if (StringUtils.isNotBlank(sessionId)) {
            querySql.append(" AND session_id =:sessionId");
            countSql.append(" AND session_id =:sessionId");
            conditionArgs.put("sessionId", sessionId);
        }
        if (StringUtils.isNotBlank(requestId)) {
            querySql.append(" AND request_id =:requestId");
            countSql.append(" AND request_id =:requestId");
            conditionArgs.put("requestId", requestId);
        }
        if (responseStatus > 0) {
            querySql.append(" AND response_status =:responseStatus");
            countSql.append(" AND response_status =:responseStatus");
            conditionArgs.put("responseStatus", responseStatus);
        }
        if (resultCode > 0) {
            querySql.append(" AND result_code =:resultCode");
            countSql.append(" AND result_code =:resultCode");
            conditionArgs.put("resultCode", resultCode);
        }
        if (StringUtils.isNotBlank(beginTime)) {
            querySql.append(" AND a.gmt_created >=:beginTime");
            countSql.append(" AND a.gmt_created >=:beginTime");
            conditionArgs.put("beginTime", beginTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            querySql.append(" AND a.gmt_created >=:endTime");
            countSql.append(" AND a.gmt_created >=:endTime");
            conditionArgs.put("endTime", endTime);
        }
        querySql.append(" ORDER BY a.gmt_created DESC,a.id DESC");

        return pageOfBeanBySql(pageInfo, countSql.toString(), querySql.toString(), SysLogDTO.class, conditionArgs);
    }
}
