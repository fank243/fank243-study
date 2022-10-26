package com.github.fank243.study.core.constants;

/**
 * Spring Security 常量池
 * 
 * @author FanWeiJie
 * @since 2022-10-01 22:07:36
 */
public class SecurityConstants {

    /** 角色前缀 */
    public static String ROLE_PREFIX = "ROLE_";

    /** 前缀 */
    public static String PROJECT_PREFIX = "study";

    /** 内部 */
    public static String FROM_IN = "Y";

    /** 标志 */
    public static String FROM = "from";

    /** 默认登录URL */
    public static String OAUTH_TOKEN_URL = "/oauth2/token";

    /** grant_type */
    public static String REFRESH_TOKEN = "refresh_token";

    /** 手机号登录 */
    public static String APP = "app";

    /** {bcrypt} 加密的特征码 */
    public static String BCRYPT = "{bcrypt}";

    /** {noop} 加密的特征码 */
    public static String NOOP = "{noop}";

    /*** 资源服务器默认bean名称 */
    public static String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

    /** 用户名 */
    public static String USERNAME = "username";

    /** 用户信息 */
    public static String DETAILS_USER = "user_info";

    /** 验证码有效期,默认 60秒 */
    public static long CODE_TIME = 60;

    /** 验证码长度 */
    public static String CODE_SIZE = "6";

    /** 客户端模式 */
    public static String CLIENT_CREDENTIALS = "client_credentials";

    /** 客户端ID */
    public static String CLIENT_ID = "clientId";

    /** 短信登录 参数名称 */
    public static String SMS_PARAMETER_NAME = "mobile";

    /** 授权码模式confirm */
    public static String CUSTOM_CONSENT_PAGE_URI = "/token/confirm_access";

}
