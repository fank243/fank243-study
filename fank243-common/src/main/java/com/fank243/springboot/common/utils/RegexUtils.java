package com.fank243.springboot.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证
 * 
 * @author FanWeiJie
 * @date 2019-04-19 15:58:41
 */
@Slf4j
public class RegexUtils {

    /**
     * 验证中国大陆手机号码
     * 
     * @param mobile 手机号码
     * @return 验证结果
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        return mobile.matches("1[3-9]\\d{9}");
    }

    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        return email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }

    /**
     * 验证用户名是否合法，由英文数字_-组成
     * 
     * @param username 密码
     * @return 验证结果
     */
    public static boolean isUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }
        return username.matches("[a-zA-Z0-9_-]{4,12}");
    }

    /**
     * 密码是否合法，英文、数字、_、.组成
     * 
     * @param password 密码
     * @return 验证结果
     */
    public static boolean isPassword(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        return password.matches("([a-zA-Z0-9_]|[._]){6,20}");
    }

    /**
     * 是否数字
     * 
     * @param number 字符串
     * @return 验证结果as_2314klj
     */
    public static boolean isNumber(String number) {
        if (StringUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("\\d+");
    }

    /**
     * 是否数字
     * 
     * @param number 字符串
     * @param num 数量
     * @return 验证结果
     */
    public static boolean isNumber(String number, int num) {
        if (StringUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("\\d{" + num + "}");
    }

    /**
     * 是否保留两位小数
     * 
     * @param number 数值
     * @return 验证结果
     */
    public static boolean isTwoDecimal(String number) {
        if (StringUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("^(([1-9]\\d*)|([0]))(\\.(\\d){0,2})?$");
    }

    /**
     * 是否身份证号码
     * 
     * @param idNo 证件号码
     * @return 验证结果
     */
    public static boolean isIdNo(String idNo) {
        if (StringUtils.isEmpty(idNo)) {
            return false;
        }
        return idNo.matches(
            "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))");
    }

    /**
     * 是否IP地址，不能排除内网IP
     * 
     * @param ip IP地址
     * @return 验证结果
     */
    public static boolean isIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        return ip.matches("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
    }

    /**
     * 是否内网IP
     * 
     * @param ip IP地址
     * @return 验证结果
     */
    public static boolean isInnerIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        return ip.matches(
            "^(127\\.0\\.0\\.1)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})$");
    }

    public static boolean isBankCard(String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            return false;
        }
        return cardNo.matches("^\\d{16,21}$");
    }

    /**
     * 是否是数字,double也能判断
     * 
     * @param str
     * @return
     */
    public static boolean isDuble(String str) {
        if (str.isEmpty() && str != "") {
            return false;
        }
        Matcher mer = Pattern.compile("^[+-]?[0-9.]+$").matcher(str);
        return mer.find();
    }
}