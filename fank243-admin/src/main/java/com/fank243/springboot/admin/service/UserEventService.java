package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.repository.UserEventRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.core.dto.UserEventDTO;
import com.fank243.springboot.core.entity.SysUserEvent;
import com.fank243.springboot.core.entity.UserEvent;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.enums.UserEventType;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户事件
 * 
 * @author FanWeiJie
 * @date 2020-03-08 14:09:36
 */
@Service
public class UserEventService extends BaseService<SysUserEvent> {
    @Resource
    private UserEventRepository repository;
    @Resource
    private IpAddrService ipAddrService;

    @Transactional(rollbackFor = Exception.class)
    public void addEvent(Long adminId, UserEventType eventType, DeviceType deviceType, String deviceNumber,
        String remark) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }
        repository.save(new UserEvent(adminId, eventType, deviceType, deviceNumber, remark, ip, addr));
    }

    public PageBean<UserEventDTO> pageOfEvent(PageInfo pageInfo, String eventType, String beginDate, String endDate) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT COUNT(*) FROM tb_user_event a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");
        querySql.append(
            "SELECT a.id,a.type,a.ip,a.ip_addr,b.username,b.device_type,b.device_number,remark,a.gmt_created FROM tb_user_event a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");

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

        return pageOfBeanBySql(pageInfo, countSql.toString(), querySql.toString(), UserEventDTO.class, conditionArgs);
    }
}
