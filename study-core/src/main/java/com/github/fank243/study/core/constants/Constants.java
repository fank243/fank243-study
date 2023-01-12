package com.github.fank243.study.core.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 常量池
 * 
 * @author FanWeiJie
 * @since 2022-09-23 16:22:43
 */
public class Constants {

    /** 基包 **/
    public static final String BASE_PACKAGE = "com.github.fank243.study";

    public static final String BASE_PACKAGE_GATEWAY = "com.github.fank243.study.gateway";

    public static final String BASE_PACKAGE_SUPPORT = "com.github.fank243.study.support";

    /** 基包 > service **/
    public static final String BASE_PACKAGE_SERVICE = "com.github.fank243.study.**.service";

    /** 基包 > mapper **/
    public static final String BASE_PACKAGE_MAPPER = "com.github.fank243.study.*.mapper";

    /** 跨服务调用安全令牌 Header Name **/
    public static final String SECURITY_TOKEN = "SecurityToken";

    /** 文件类型 **/
    public static final List<String> FILE_TYPE =
        Arrays.asList("txt", "xls", "xlsx", "png", "bmp", "gif", "jpg", "jpeg", "docx", "pdf");

    /** 文件类型 > 图片文件 **/
    public static final List<String> FILE_TYPE_IMG = Arrays.asList("png", "bmp", "gif", "jpg", "jpeg");

    /** 文件类型 > 办公类文件 **/
    public static final List<String> FILE_TYPE_OFFICE = Arrays.asList("xls", "xlsx", "docx", "pdf");

    /** 文件大小(MB) **/
    public static final int FILE_SIZE = 10;

    /** 文件大小 > 图片文件大小(MB) **/
    public static final int FILE_SIZE_IMG = 2;

    /** 文件大小 > 办公类文件大小(MB) **/
    public static final int FILE_SIZE_OFFICE = 10;

    public static final String PUNCTUATION_POINT = ".";
}
