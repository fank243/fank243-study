package com.fank243.springboot.core.consts;

/**
 * Redis Key
 * 
 * @author FanWeiJie
 * @date 2019-10-28 13:49:22
 */
public class RedisKey {

    /** 短信发送记录 */
    public static final String SMS_RECORD = "notice::sms-record";
    /** 邮件发送记录 */
    public static final String EMAIL_RECORD = "notice::email-record";

    /** 短信验证码 **/
    public static final String SMS_CODE = "notice::sms-code";
    /** 邮件验证码 **/
    public static final String EMAIL_CODE = "notice::email-code";

    /** 系统配置表 **/
    public static final String SYS_CONFIG = "sys::config";

}
