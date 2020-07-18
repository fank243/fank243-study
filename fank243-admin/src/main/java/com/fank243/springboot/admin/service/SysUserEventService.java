package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.repository.SysUserEventRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.core.dto.SysUserEventDTO;
import com.fank243.springboot.core.entity.SysUserEvent;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员事件
 * 
 * @author FanWeiJie
 * @date 2020-03-08 14:09:36
 */
@Service
public class SysUserEventService extends BaseService<SysUserEvent> {
    @Resource
    private SysUserEventRepository repository;
    @Resource
    private IpAddrService ipAddrService;

    @Transactional(rollbackFor = Exception.class)
    public void addEvent(Long sysUserId, SysUserEventType eventType, String remark) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }
        repository.save(new SysUserEvent(sysUserId, eventType, remark, ip, addr));
    }

    public PageBean<SysUserEventDTO> pageOfEvent(PageInfo pageInfo, String eventType, String beginDate,
        String endDate) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql
            .append("SELECT COUNT(*) FROM tb_sys_user_event a LEFT JOIN tb_sys_user b ON a.sys_user_id=b.id WHERE 1=1");
        querySql.append(
            "SELECT a.id,a.type,a.ip,a.ip_addr,b.username,remark,a.gmt_created FROM tb_sys_user_event a LEFT JOIN tb_sys_user b ON a.sys_user_id=b.id WHERE 1=1");

        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(eventType)) {
            countSql.append(" AND type =:eventType");
            querySql.append(" AND type =:eventType");
            conditionArgs.put("eventType", eventType);
        }
        if (StringUtils.isNotBlank(beginDate)) {
            countSql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') >=:beginDate");
            querySql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') >=:beginDate");
            conditionArgs.put("beginDate", beginDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            countSql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') <=:endDate");
            querySql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') <=:endDate");
            conditionArgs.put("endDate", endDate);
        }
        querySql.append(" ORDER BY a.gmt_created DESC,a.id DESC");

        return pageOfBeanBySql(pageInfo, countSql.toString(), querySql.toString(), SysUserEventDTO.class,
            conditionArgs);
    }
}
