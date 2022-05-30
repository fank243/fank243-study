package com.fank243.study.core.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;

/**
 * 客户端工具类
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@SuppressWarnings("unused")
public class ServletUtils {

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return Objects.requireNonNull(getRequest()).getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(Objects.requireNonNull(getRequest()).getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(Objects.requireNonNull(getRequest()).getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(Objects.requireNonNull(getRequest()).getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        try {
            return Objects.requireNonNull(getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        try {
            return Objects.requireNonNull(getRequestAttributes()).getResponse();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return Objects.requireNonNull(getRequest()).getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes)attributes;
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 将字符串渲染到客户端
     * 
     * @param response 渲染对象
     * @param str 待渲染的字符串
     */
    public static void renderStr(HttpServletResponse response, String str) {
        ServletUtil.write(response, str, ContentType.TEXT_PLAIN.getValue());
    }

    /**
     * 将JSON字符串渲染到客户端
     * 
     * @param response 渲染对象
     * @param str 待渲染的字符串
     */
    public static void renderJson(HttpServletResponse response, String str) {
        ServletUtil.write(response, str, ContentType.JSON.getValue());
    }

    /**
     * 将输入流渲染到客户端
     * 
     * @param response 渲染对象
     * @param inputStream 输入流
     */
    public static void renderStream(HttpServletResponse response, InputStream inputStream) {
        ServletUtil.write(response, inputStream);
    }

    /**
     * 将文件渲染到客户端
     * 
     * @param response 渲染对象
     * @param file 文件
     */
    public static void renderFile(HttpServletResponse response, File file) {
        ServletUtil.write(response, file);
    }

    /**
     * 是否是Ajax异步请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        String accept = request.getHeader(Header.ACCEPT.getValue());
        if (accept != null && accept.contains(ContentType.JSON.getValue())) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StrUtil.containsAny(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return StrUtil.containsAny(ajax, "json", "xml");
    }

    /**
     * 内容编码
     * 
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    /**
     * 内容解码
     * 
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }
}
