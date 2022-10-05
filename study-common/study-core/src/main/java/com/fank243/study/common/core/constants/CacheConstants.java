package com.fank243.study.common.core.constants;

/**
 * Cache Key
 * 
 * @author FanWeiJie
 * @since 2022-09-26 14:03:40
 */
public class CacheConstants {
    /** 跨服务调用安全令牌 **/
    public static final String SECURITY_TOKEN = "security:token:";

    /** Oauth2 授权令牌 **/
    public static final String OAUTH2_TOKEN = "studyToken:login:oauth2:";

    /** 图形验证码 **/
    public static final String IMG_CODE_KEY = "security:code:image:";

    /** 短信验证码 **/
    public static final String SMS_CODE_KEY = "security:code:sms:";

    /** 手机号码发送短信锁定 **/
    public static final String SMS_MOBILE_LOCK = "security:mobile:";

}
