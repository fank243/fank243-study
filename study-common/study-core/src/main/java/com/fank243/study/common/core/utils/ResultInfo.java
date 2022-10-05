package com.fank243.study.common.core.utils;

import java.io.Serializable;

import com.fank243.study.common.core.constants.enums.ResultCodeEnum;

import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * 系统统一消息组件
 *
 * @apiNote 可用于HTTP响应JSON串，也可用用于接收被调用层返回处理结果
 * @author FanWeiJie
 * @since 2018-09-31 10:10:10
 */
@Data
public class ResultInfo<T> implements Serializable {

    /** 响应状态码 */
    private int status = ResultCodeEnum.R500.getStatus();

    /** 错误消息 */
    private String message = ResultCodeEnum.R500.getMessage();

    /** 异常消息 */
    private String error = "";

    /**
     * 成功标志符
     *
     * 如果成功则结果一定为true
     *
     * 如果失败则结果一定为false，同时可提供code的值作进一步处理
     */
    private boolean success = Boolean.FALSE;

    /** 时间戳 **/
    private Long timestamp = System.currentTimeMillis();

    /** 请求地址 */
    private String path = "";

    /** 数据载体 */
    private T payload;

    public boolean isSuccess() {
        return success;
    }

    public ResultInfo<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResultInfo<T> payload(T payload) {
        this.payload = payload;
        return this;
    }

    public ResultInfo<T> path(String path) {
        this.path = path;
        return this;
    }

    public ResultInfo<T> error(String error) {
        this.error = error;
        return this;
    }

    private static <T> ResultInfo<T> builder(boolean success, int status, String message, T payload) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.success = success;
        resultInfo.status = status;
        resultInfo.message = message;
        resultInfo.payload = payload;
        return resultInfo;
    }

    private static <T> ResultInfo<T> builder(boolean success, int status, String message, String error) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.success = success;
        resultInfo.status = status;
        resultInfo.message = message;
        resultInfo.error = error;
        return resultInfo;
    }

    private static <T> ResultInfo<T> builderSuccess(int status, String message, T payload) {
        return builder(Boolean.TRUE, status, message, payload);
    }

    private static <T> ResultInfo<T> builderFail(ResultCodeEnum resultCodeEnum) {
        return builder(Boolean.FALSE, resultCodeEnum.getStatus(), resultCodeEnum.getMessage(), null);
    }

    private static <T> ResultInfo<T> builderFail(int status, String message) {
        return builder(Boolean.FALSE, status, message, null);
    }

    private static <T> ResultInfo<T> builderError(int status, String message, String error) {
        return builder(Boolean.FALSE, status, message, error);
    }

    public static <T> ResultInfo<T> ok(String message, T payload) {
        return builderSuccess(ResultCodeEnum.R200.getStatus(), message, payload);
    }

    public static <T> ResultInfo<T> ok(T payload) {
        return ok(ResultCodeEnum.R200.getMessage(), payload);
    }

    public static <T> ResultInfo<T> ok() {
        return ok(ResultCodeEnum.R200.getMessage(), null);
    }

    public static <T> ResultInfo<T> fail(int status, String message) {
        return builderFail(status, message);
    }

    public static <T> ResultInfo<T> fail(String message) {
        return fail(ResultCodeEnum.R500.getStatus(), message);
    }

    public static <T> ResultInfo<T> fail() {
        return fail(ResultCodeEnum.R500.getMessage());
    }

    public static <T> ResultInfo<T> err400(String message, String error) {
        return builderError(ResultCodeEnum.R400.getStatus(), message, error);
    }

    public static <T> ResultInfo<T> err400(String message) {
        return builderFail(ResultCodeEnum.R400.getStatus(), message);
    }

    public static <T> ResultInfo<T> err401(String message, String error) {
        return builderError(ResultCodeEnum.R401.getStatus(), message, error);
    }

    public static <T> ResultInfo<T> err401(String message) {
        return builderFail(ResultCodeEnum.R401.getStatus(), message);
    }

    public static <T> ResultInfo<T> err401() {
        return err401(ResultCodeEnum.R401.getMessage());
    }

    public static <T> ResultInfo<T> err403(String message, String error) {
        return builderError(ResultCodeEnum.R403.getStatus(), message, error);
    }

    public static <T> ResultInfo<T> err403(String message) {
        return builderFail(ResultCodeEnum.R403.getStatus(), message);
    }

    public static <T> ResultInfo<T> err403() {
        return err403(ResultCodeEnum.R403.getMessage());
    }

    public static <T> ResultInfo<T> err404() {
        return builderFail(ResultCodeEnum.R404);
    }

    public static <T> ResultInfo<T> err405() {
        return builderFail(ResultCodeEnum.R405);
    }

    public static <T> ResultInfo<T> err429(String message) {
        return builderFail(ResultCodeEnum.R429.getStatus(), message);
    }

    public static <T> ResultInfo<T> error(int status, String message, String error) {
        return builderError(status, message, error);
    }

    public static <T> ResultInfo<T> error(ResultCodeEnum resultCodeEnum, String error) {
        return error(resultCodeEnum.getStatus(), resultCodeEnum.getMessage(), error);
    }

    public static <T> ResultInfo<T> error(String message, String error) {
        return error(ResultCodeEnum.R500.getStatus(), message, error);
    }

    public static <T> ResultInfo<T> err503() {
        return builderFail(ResultCodeEnum.R503);
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
