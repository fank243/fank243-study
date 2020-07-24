package com.fank243.springboot.core.annotation;

import com.fank243.springboot.core.enums.AppLogType;
import com.fank243.springboot.core.enums.LogLevel;
import com.fank243.springboot.core.enums.SysLogType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 业务日志
 *
 * @author FanWeiJie
 * @date 2019-05-21 21:57:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AppLog {
    /** 接口描述 **/
    String desc();

    /** 日志类别 **/
    AppLogType logType();

    /** 日志级别 **/
    LogLevel logLevel() default LogLevel.INFO;
}
