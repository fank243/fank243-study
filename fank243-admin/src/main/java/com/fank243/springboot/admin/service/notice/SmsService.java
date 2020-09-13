package com.fank243.springboot.admin.service.notice;

import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.consts.ConfConfig;
import com.fank243.springboot.common.redis.RedisKey;
import com.fank243.springboot.common.utils.CacheUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.consts.SysKey;
import com.fank243.springboot.core.entity.TemplateNotice;
import com.fank243.springboot.core.enums.TemplateType;
import com.fank243.springboot.core.enums.TemplateCode;
import com.fank243.springboot.third.service.aliyun.AliyunSmsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 短信通知服务
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:48:12
 */
@Component
public class SmsService {
    @Resource
    private SmsRecordService smsRecordService;
    @Resource
    private ConfConfig confConfig;
    @Resource
    private AliyunSmsService aliyunSmsService;
    @Resource
    private TemplateNoticeService templateNoticeService;

    /** 发送短信验证码 **/
    public ResultInfo sendVerifyCode(String mobile) {
        TemplateNotice templateNotice =
            templateNoticeService.findByCodeAndType(TemplateCode.VER_CODE.name(), TemplateType.SMS.getCode());

        if (templateNotice == null) {
            return ResultInfo.fail("通知模板不存在或已被禁用");
        }

        // 限制短信发送频率
        Object obj = CacheUtils.hashGet(RedisKey.SMS_RECORD, mobile);
        if (obj != null) {
            long timestamp = Long.parseLong(String.valueOf(obj));
            if (System.currentTimeMillis() - timestamp <= 60 * 1000) {
                return ResultInfo.fail("短信发送过于频繁，请稍后重试");
            }
            CacheUtils.remove(RedisKey.SMS_RECORD, mobile);
        }

        int smsDayMax = StrUtils.strToInt(CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.SMS_DAY_MAX) + "", 10);
        // 限制短信发送量
        int counts = smsRecordService.countByMobileAndDate(mobile);
        if (counts > smsDayMax) {
            return ResultInfo.fail("当日短信可用额度已用完");
        }

        // 验证码
        String number = StrUtils.randomNum(6) + "";
        String content = templateNotice.getContent().replaceAll("#code#", number);

        // 发送短信
        JSONObject smsJson = new JSONObject();
        smsJson.put("code", number);
        ResultInfo result = sendSms(mobile, content, smsJson.toString(), templateNotice.getAliyunCode());
        if (result.isSuccess()) {
            // 发送记录
            CacheUtils.hashPut(RedisKey.SMS_RECORD, mobile, System.currentTimeMillis());
            // 验证码
            smsJson.put("timestamp", System.currentTimeMillis());
            CacheUtils.hashPut(RedisKey.SMS_CODE, mobile, smsJson);
        }

        return result;
    }

    /** 发送短信 **/
    public ResultInfo sendSms(String mobile, String content, String json, String aliyunCode) {
        // 测试环境不发送短信
        if (!IConsts.MODE_PROD.equalsIgnoreCase(confConfig.getMode())) {
            return ResultInfo.ok().message("测试环境不发送短信");
        }

        String accessKeyId = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.ALIYUN_ACCESS_KEY_ID) + "";
        String accessSecret = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.ALIYUN_ACCESS_SECRET) + "";
        String signName = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.SMS_SIGN_NAME) + "";
        ResultInfo result =
            aliyunSmsService.sendSms(accessKeyId, accessSecret, signName, aliyunCode, mobile, json);
        if (!result.isSuccess()) {
            return result;
        }

        // 添加短信记录
        return smsRecordService.addRecord(mobile, content);
    }

    /** 发送短信(批量) **/
    public ResultInfo sendBatchSms(List<String> mobileList, String content, String json, String templateCode) {
        // 测试环境不发送短信
        if (!IConsts.MODE_PROD.equalsIgnoreCase(confConfig.getMode())) {
            return ResultInfo.ok().message("测试环境不发送短信");
        }

        String accessKeyId = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.ALIYUN_ACCESS_KEY_ID) + "";
        String accessSecret = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.ALIYUN_ACCESS_SECRET) + "";
        String signName = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.SMS_SIGN_NAME) + "";
        ResultInfo result = aliyunSmsService.sendSms(accessKeyId, accessSecret, signName, templateCode,
            String.join(",", mobileList), json);
        if (!result.isSuccess()) {
            return result;
        }

        // 添加短信记录
        return smsRecordService.batchAddRecord(mobileList, content);
    }

}
