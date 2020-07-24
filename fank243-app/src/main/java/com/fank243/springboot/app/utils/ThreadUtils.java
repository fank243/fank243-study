package com.fank243.springboot.app.utils;

import com.fank243.springboot.app.model.AppLogContext;

/**
 * 线程工具类
 * 
 * @author FanWeiJie
 * @date 2020-07-24 14:19:30
 */
public class ThreadUtils {

    private final static ThreadLocal<AppLogContext> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(AppLogContext appLogContext) {
        THREAD_LOCAL.set(appLogContext);
    }

    public static AppLogContext get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
