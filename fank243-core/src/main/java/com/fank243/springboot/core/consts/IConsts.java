package com.fank243.springboot.core.consts;

import java.util.Arrays;
import java.util.List;

/**
 * 常量池
 *
 * @author FanWeiJie
 * @date 2019-10-25 14:18:25
 */
public class IConsts {

    /** 开发模式 **/
    public static final String MODE_DEV = "dev";
    /** 演示模式 **/
    public static final String MODE_TEST = "test";
    /** 生产模式 **/
    public static final String MODE_PROD = "prod";

    /** 请求头AccessToken **/
    public static final String ACCESS_TOKEN = "AccessToken";

    /** 表单认证令牌 **/
    public static final String AUTHENTICITY_TOKEN = "authenticityToken";

    /** 默认图片 **/
    public static String DEFAULT_IMG = "/images/default.png";

    /** 默认头像 **/
    public static String DEFAULT_PHOTO = "/images/photo.jpg";

    /** 验证码有效时长：毫秒 **/
    public static Long codeTimes = 15 * 60 * 1000L;

    /** Api 版本号 **/
    public static List<Integer> apiVerList = Arrays.asList(10);

    public static class RoleType {
        public static final String ROOT = "role:root";

        public static final String ADMIN = "role:admin";
    }
}
