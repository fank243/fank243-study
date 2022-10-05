package com.fank243.study.log.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 日志类型
 * 
 * @author FanWeiJie
 * @since 2022-10-04 23:40:26
 */
@Getter
@RequiredArgsConstructor
public enum LogTypeEnum {

    /** 正常日志 */
    NORMAL("00", "正常日志"),

    /** 错误日志 */
    ERROR("99", "错误日志");

    /** 类型 */
    private final String logType;

    /** 描述 */
    private final String logDesc;

}
