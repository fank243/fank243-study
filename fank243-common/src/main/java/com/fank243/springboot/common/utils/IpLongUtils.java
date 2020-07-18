package com.fank243.springboot.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * IP 地址转换工具
 * 
 * @author Mikan
 * @date 2015-09-22 10:59
 */
public class IpLongUtils {
    /**
     * 把字符串IP转换成long
     *
     * @param ipStr 字符串IP
     * @return IP对应的long值
     */
    public static long ip2Long(String ipStr) {
        if (StringUtils.isBlank(StrUtils.strToStr(ipStr, ""))) {
            return 0L;
        }
        if (ipStr.startsWith("0:")) {
            return 2130706433L;
        }

        String[] ip = ipStr.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8)
            + Long.parseLong(ip[3]);
    }

    /**
     * 把IP的long值转换成字符串
     *
     * @param ipLong IP的long值
     * @return long值对应的字符串
     */
    public static String long2Ip(long ipLong) {
        StringBuilder ip = new StringBuilder();
        ip.append(ipLong >>> 24).append(".");
        ip.append((ipLong >>> 16) & 0xFF).append(".");
        ip.append((ipLong >>> 8) & 0xFF).append(".");
        ip.append(ipLong & 0xFF);
        return ip.toString();
    }

    public static void main(String[] args) {
        System.out.println(ip2Long("127.0.0.1"));
        System.out.println(long2Ip(3232235521L));
        System.out.println(ip2Long("10.0.0.1"));
    }

}