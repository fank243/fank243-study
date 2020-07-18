package com.fank243.springboot.admin.service.notice;

import com.fank243.springboot.admin.repository.notice.EmailRecordRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.dto.EmailRecordDTO;
import com.fank243.springboot.core.entity.EmailRecord;
import com.fank243.springboot.core.entity.SmsRecord;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
@Service
public class EmailRecordService extends BaseService<SmsRecord> {
    @Resource
    private IpAddrService ipAddrService;
    @Resource
    private EmailRecordRepository repository;

    public ResultInfo addRecord(String email, String title, String content) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }

        EmailRecord record = new EmailRecord();
        record.setEmail(email);
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

    public ResultInfo batchAddRecord(List<String> emailList, String title, String content) {
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }

        List<EmailRecord> list = new ArrayList<>();
        for (String email : emailList) {
            EmailRecord record = new EmailRecord();
            record.setEmail(email);
            record.setTitle(title);
            record.setContent(content);
            record.setIp(ip);
            record.setIpAddr(addr);
        }

        List<EmailRecord> records = repository.saveAll(list);
        if (records.size() != list.size()) {
            return ResultInfo.fail("部分记录添加失败");
        }

        return ResultInfo.ok();
    }

    public int countByEmailAndDate(String email) {
        return repository.countByEmailAndDate(email);
    }

    public PageBean<EmailRecordDTO> pageOfEmailRecord(PageInfo pageInfo, String keyword, String startDate,
        String endDate) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT count(*) FROM tb_record_email a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");
        querySql.append(
                "SELECT a.id,a.email,a.title,a.content,a.ip,a.ip_addr,a.gmt_created,b.username FROM tb_record_email a LEFT JOIN tb_user b ON a.user_id=b.id WHERE 1=1");

        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(keyword)) {
            countSql.append(" AND (username like :keyword or a.email like :keyword)");
            querySql.append(" AND (username like :keyword or a.email like :keyword)");
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

        return pageOfBeanBySql(pageInfo, countSql.toString(), querySql.toString(), EmailRecordDTO.class, conditionArgs);
    }
}
