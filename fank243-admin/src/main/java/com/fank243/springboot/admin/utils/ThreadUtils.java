package com.fank243.springboot.admin.utils;

/**
 * 线程工具类
 * 
 * @author FanWeiJie
 * @date 2020-07-24 14:19:30
 */
public class ThreadUtils {

    private final static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String requestId) {
        THREAD_LOCAL.set(requestId);
    }

    public static String get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
