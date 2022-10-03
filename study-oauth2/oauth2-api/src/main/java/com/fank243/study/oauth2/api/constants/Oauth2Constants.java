package com.fank243.study.oauth2.api.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Oauth2 常量池
 * 
 * @author FanWeiJie
 * @since 2022-10-03 03:16:50
 */
public class Oauth2Constants {

    /** 授权类型 **/
    public enum GrantType {
        /** 密码模式 **/
        PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, CLIENT_CREDENTIALS, IMPLICIT
    }

    /** 权限范围 **/
    public enum Scope {
        /** 用户基本信息 **/
        USER_INFO,

        /** 添加用户 **/
        USER_MODIFY,

        ;

        public static final List<String> SCOPE_ALL =
            Arrays.asList(USER_INFO.name().toLowerCase(), USER_MODIFY.name().toLowerCase());
    }
}
