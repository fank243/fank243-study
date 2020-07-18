package com.fank243.springboot.admin.service.notice;

import com.fank243.springboot.admin.repository.notice.PushRecordRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.dto.PushRecordDTO;
import com.fank243.springboot.core.entity.PushRecord;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 推送消息送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:13:49
 */
@Service
public class PushRecordService extends BaseService<PushRecord> {
    @Resource
    private IpAddrService ipAddrService;
    @Resource
    private PushRecordRepository repository;

    public ResultInfo addRecord(Long userId, DeviceType deviceType, String deviceToken, String title, String content) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }

        PushRecord record = new PushRecord();
        record.setUserId(userId);
        record.setDeviceType(deviceType);
        record.setDeviceToken(deviceToken);
        record.setTitle(title);
        record.setContent(content);
        record.setIp(ip);
        record.setIpAddr(addr);

        record = repository.save(record);

        if (record.getId() == null || record.getId() <= 0) {
            return ResultInfo.fail("添加记录失败");
        }

        return ResultInfo.ok();
    }

    public ResultInfo batchAddRecord(List<PushRecord> pushRecords) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }

        for (PushRecord record : pushRecords) {
            record.setIp(ip);
            record.setIpAddr(addr);
        }

        List<PushRecord> records = repository.saveAll(pushRecords);
        if (records.size() != pushRecords.size()) {
            return ResultInfo.fail("部分记录添加失败");
        }

        return ResultInfo.ok();
    }

    public PageBean<PushRecordDTO> pageOfPushRecord(PageInfo pageInfo, String username, String startDate,
        String endDate) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT COUNT(*) FROM tb_record_push a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");
        querySql.append(
            "SELECT a.id,a.device_type,a.device_token,a.ip,a.ip_addr,b.username,b.device_type,b.device_number,remark,a.gmt_created FROM tb_record_push a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");

        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(username)) {
            countSql.append(" AND username =:username");
            querySql.append(" AND username =:username");
            conditionArgs.put("username", "%" + username + "%");
        }
        if (StringUtils.isNotBlank(endDate)) {
            countSql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') >=:startDate");
            querySql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') >=:startDate");
            conditionArgs.put("startDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            countSql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') <=:endDate");
            querySql.append(" AND date_format(a.gmt_created,'%Y-%m-%d') <=:endDate");
            conditionArgs.put("endDate", endDate);
        }
        querySql.append(" ORDER BY a.gmt_created DESC,a.id DESC");

        return pageOfBeanBySql(pageInfo, countSql.toString(), querySql.toString(), PushRecordDTO.class, conditionArgs);
    }
}
