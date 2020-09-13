package com.fank243.springboot.admin.service.notice;

import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.consts.ConfConfig;
import com.fank243.springboot.common.redis.RedisKey;
import com.fank243.springboot.common.utils.CacheUtils;
import com.fank243.springboot.common.utils.EmailUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.consts.SysKey;
import com.fank243.springboot.core.entity.TemplateNotice;
import com.fank243.springboot.core.enums.TemplateType;
import com.fank243.springboot.core.enums.TemplateCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 邮件通知服务
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:48:12
 */
@Component
public class EmailService {
    @Resource
    private EmailRecordService emailRecordService;
    @Resource
    private ConfConfig confConfig;
    @Resource
    private TemplateNoticeService templateNoticeService;

    /** 发送邮件验证码 **/
    public ResultInfo sendVerifyCode(String email) {
        TemplateNotice templateNotice =
            templateNoticeService.findByCodeAndType(TemplateCode.VER_CODE.name(), TemplateType.EMAIL.getCode());
        if (templateNotice == null) {
            return ResultInfo.fail("通知模板不存在或已被禁用");
        }

        // 限制邮件发送频率
        Object obj = CacheUtils.hashGet(RedisKey.EMAIL_RECORD, email);
        if (obj != null) {
            long timestamp = Long.parseLong(String.valueOf(obj));
            if (System.currentTimeMillis() - timestamp <= 60 * 1000) {
                return ResultInfo.fail("邮件发送过于频繁，请稍后重试");
            }
            CacheUtils.remove(RedisKey.EMAIL_RECORD, email);
        }

        int emailDayMax = StrUtils.strToInt(CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_DAY_MAX) + "", 30);
        // 限制邮件发送量
        int counts = emailRecordService.countByEmailAndDate(email);
        if (counts > emailDayMax) {
            return ResultInfo.fail("当日邮件可用额度已用完");
        }

        // 验证码
        String number = StrUtils.randomNum(6) + "";
        String content = templateNotice.getContent().replaceAll("#code#", number);

        // 发送邮件
        ResultInfo result = sendEmail(email, templateNotice.getTitle(), content);
        if (result.isSuccess()) {
            // 发送记录
            CacheUtils.hashPut(RedisKey.EMAIL_RECORD, email, System.currentTimeMillis());
            // 验证码
            JSONObject json = new JSONObject();
            json.put("code", number);
            json.put("timestamp", System.currentTimeMillis());
            CacheUtils.hashPut(RedisKey.EMAIL_CODE, email, json);
        }

        return result;
    }

    /** 发送邮件 **/
    public ResultInfo sendEmail(String email, String title, String content) {
        // 测试环境不发送邮件
        if (!IConsts.MODE_PROD.equalsIgnoreCase(confConfig.getMode())) {
            return ResultInfo.ok().message("测试环境不发送邮件");
        }

        String username = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_USERNAME) + "";
        String password = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_PASSWORD) + "";
        String hostname = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_SMTP) + "";
        String port = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_PORT) + "";
        String nickname = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_NICKNAME) + "";
        EmailUtils emailUtils = new EmailUtils(hostname, Integer.parseInt(port), nickname, username, password);
        ResultInfo result;
        try {
            String msgId = emailUtils.sendHtmlEmail(email, email, title, content, "抱歉，您的邮箱不支持HTML", true);

            result = StringUtils.isNotBlank(msgId) ? ResultInfo.ok() : ResultInfo.fail("邮件发送失败");
        } catch (EmailException e) {
            e.printStackTrace();
            return ResultInfo.fail("邮箱通道异常，请稍后再试");
        }
        if (!result.isSuccess()) {
            return result;
        }

        // 添加记录
        return emailRecordService.addRecord(email, title, content);
    }

    /** 发送邮件(批量) **/
    public ResultInfo sendBatchEmail(List<String> emailList, String title, String content) {
        // 测试环境不发送邮件
        if (!IConsts.MODE_PROD.equalsIgnoreCase(confConfig.getMode())) {
            return ResultInfo.ok().message("测试环境不发送邮件");
        }

        String username = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_USERNAME) + "";
        String password = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_PASSWORD) + "";
        String hostname = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_SMTP) + "";
        String port = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_PORT) + "";
        String nickname = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.EMAIL_NICKNAME) + "";
        EmailUtils emailUtils = new EmailUtils(hostname, Integer.parseInt(port), nickname, username, password);
        ResultInfo result;
        try {
            String msgId = emailUtils.sendHtmlEmail(emailList, title, content, "抱歉，您的邮箱不支持HTML", true);

            result = StringUtils.isNotBlank(msgId) ? ResultInfo.ok() : ResultInfo.fail("邮件发送失败");
        } catch (EmailException e) {
            e.printStackTrace();
            return ResultInfo.fail("邮箱通道异常，请稍后再试");
        }
        if (!result.isSuccess()) {
            return result;
        }

        // 添加记录
        return emailRecordService.batchAddRecord(emailList, title, content);
    }

}
