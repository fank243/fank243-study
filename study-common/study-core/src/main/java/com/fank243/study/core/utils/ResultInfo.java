package com.fank243.study.core.utils;

import cn.hutool.json.JSONUtil;
import com.fank243.study.core.enums.ResultCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统统一消息组件，可用于HTTP 响应JSON串，也可用用于接收被调用层返回处理结果
 *
 * @author FanWeiJie
 * @date 2018-09-31 10:10:10
 */
@SuppressWarnings("unchecked")
@Builder
@Data
public class ResultInfo<T> implements Serializable {

    /** 响应状态码 */
    private int status;

    /** 错误消息 */
    private String message;

    /** ERROR */
    private String error;

    /**
     * 成功标志符
     *
     * 如果成功则结果一定为true
     *
     * 如果失败则结果一定为false，同时可提供code的值作进一步处理
     */
    private boolean success;

    /** 时间戳 **/
    private Long timestamp;

    /** 请求URI */
    private String path;

    private T payload;

    public static <T> ResultInfo<T> build(boolean isSuccess, int status, String message, T payload) {
        // @@formatter:off
        ResultInfo<Object> result = builder()
            .success(isSuccess).status(status).message(message).payload(payload).timestamp(System.currentTimeMillis())
            .build();
        // @@formatter:on
        return (ResultInfo<T>)result;
    }

    public static <T> ResultInfo<T> build(String message, String error) {
        // @@formatter:off
        ResultInfo<Object> result = builder()
            .success(Boolean.FALSE).status(ResultCodeEnum.R500.getStatus()).message(message).error(error).timestamp(System.currentTimeMillis())
            .build();
        // @@formatter:on
        return (ResultInfo<T>)result;
    }

    public static <T> ResultInfo<T> ok(String message, T payload) {
        return build(Boolean.TRUE, ResultCodeEnum.R200.getStatus(), message, payload);
    }

    public static <T> ResultInfo<T> ok(T payload) {
        return ok(ResultCodeEnum.R200.getMessage(), payload);
    }

    public static <T> ResultInfo<T> ok() {
        return ok(ResultCodeEnum.R200.getMessage(), null);
    }

    public static <T> ResultInfo<T> fail(int status, String message, T payload) {
        return build(Boolean.FALSE, status, message, payload);
    }

    public static <T> ResultInfo<T> fail(int status, String message) {
        return fail(status, message, null);
    }

    public static <T> ResultInfo<T> fail(String message) {
        return fail(ResultCodeEnum.R500.getStatus(), message, null);
    }

    public static <T> ResultInfo<T> fail() {
        return fail(ResultCodeEnum.R500.getStatus(), ResultCodeEnum.R500.getMessage(), null);
    }

    // ERROR
    public static <T> ResultInfo<T> error(String message, String error) {
        return build(message, error);
    }

    public static <T> ResultInfo<T> error(String error) {
        return build(ResultCodeEnum.R500.getMessage(), error);
    }

    public static <T> ResultInfo<T> err401(String message) {
        return build(Boolean.FALSE, ResultCodeEnum.R401.getStatus(), message, null);
    }

    public static <T> ResultInfo<T> err401() {
        return err401(ResultCodeEnum.R401.getMessage());
    }

    public static <T> ResultInfo<T> e403(String message) {
        return build(Boolean.FALSE, ResultCodeEnum.R403.getStatus(), ResultCodeEnum.R403.getMessage(), null);
    }

    public static <T> ResultInfo<T> err403() {
        return e403(ResultCodeEnum.R403.getMessage());
    }

    public static <T> ResultInfo<T> err404() {
        return build(Boolean.FALSE, ResultCodeEnum.R404.getStatus(), ResultCodeEnum.R404.getMessage(), null);
    }

    public static <T> ResultInfo<T> err405() {
        return build(Boolean.FALSE, ResultCodeEnum.R405.getStatus(), ResultCodeEnum.R405.getMessage(), null);
    }

    public static <T> ResultInfo<T> err503() {
        return build(Boolean.FALSE, ResultCodeEnum.R503.getStatus(), ResultCodeEnum.R503.getMessage(), null);
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}