package com.fank243.springboot.admin.service.notice;

import com.fank243.springboot.admin.repository.notice.SmsRecordRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.dto.SmsRecordDTO;
import com.fank243.springboot.core.entity.SmsRecord;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:13:49
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SmsRecordService extends BaseService<SmsRecord> {

    @Resource
    private SmsRecordRepository repository;
    @Resource
    private IpAddrService ipAddrService;

    public ResultInfo addRecord(String mobile, String content) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }

        SmsRecord record = new SmsRecord();
        record.setMobile(mobile);
        record.setContent(content);
        record.setIp(ip);
        record.setIpAddr(addr);

        record = repository.save(record);

        if (record.getId() == null || record.getId() <= 0) {
            return ResultInfo.fail("添加记录失败");
        }

        return ResultInfo.ok();
    }

    public ResultInfo batchAddRecord(List<String> mobileList, String content) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }

        List<SmsRecord> list = new ArrayList<>();
        for (String mobile : mobileList) {
            SmsRecord record = new SmsRecord();
            record.setMobile(mobile);
            record.setContent(content);
            record.setIp(ip);
            record.setIpAddr(addr);
        }

        List<SmsRecord> records = repository.saveAll(list);
        if (records.size() != list.size()) {
            return ResultInfo.fail("部分记录添加失败");
        }

        return ResultInfo.ok();
    }

    public int countByMobileAndDate(String mobile) {
        return repository.countByMobileAndDate(mobile);
    }

    public PageBean<SmsRecordDTO> pageOfSmsRecord(PageInfo pageInfo, String keyword, String startDate, String endDate) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT count(*) FROM tb_record_sms a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");
        querySql.append(
            "SELECT a.id,a.mobile,a.content,a.ip,a.ip_addr,a.gmt_created,b.username FROM tb_record_sms a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");

        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(keyword)) {
            countSql.append(" AND (username like :keyword or a.mobile like :keyword)");
            querySql.append(" AND (username like :keyword or a.mobile like :keyword)");
            conditionArgs.put("keyword", "%" + keyword + "%");
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

        return pageOfBeanBySql(pageInfo, countSql.toString(), querySql.toString(), SmsRecordDTO.class, conditionArgs);
    }
}
