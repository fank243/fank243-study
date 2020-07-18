package com.fank243.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统统一消息组件，可用于HTTP 响应JSON串，也可用用于接收被调用层返回处理结果
 *
 * @author FanWeiJie
 * @date 2018-09-31 10:10:10
 */
@ApiModel("响应组件")
@Data
public class ResultInfo implements Serializable {

    /** 响应代码 */
    @ApiModelProperty(value = "响应代码", required = true, dataType = "Integer")
    public int code = ResultCode.R500.getCode();

    /** 错误消息 */
    @ApiModelProperty(value = "响应消息", required = true, dataType = "String")
    public String msg = ResultCode.R500.getMsg();

    /**
     * 成功标志符
     *
     * 如果成功则结果一定为true
     *
     * 如果失败则结果一定为false，同时可提供code的值作进一步处理
     */
    @ApiModelProperty(value = "成功标志符", required = true, dataType = "boolean", allowableValues = "true,false")
    private boolean success;

    /** 时间戳 **/
    @ApiModelProperty(value = "响应时间戳", required = true, dataType = "Integer")
    private Long timestamp = System.currentTimeMillis();

    /** 结果集中的对象 */
    @ApiModelProperty(value = "响应数据集", required = true, dataType = "Object")
    private Object payload = "";

    public ResultInfo() {}

    public ResultInfo(boolean success) {
        this.timestamp = System.currentTimeMillis();
        this.success = success;
    }

    public ResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(int code, String msg, Object payload) {
        this.code = code;
        this.msg = msg;
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResultInfo payload(Object payload) {
        this.payload = payload == null ? "" : payload;
        return this;
    }

    public ResultInfo code(int code) {
        this.code = code;
        return this;
    }

    public ResultInfo message(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultInfo success(boolean success) {
        this.success = success;
        return this;
    }

    public static ResultInfo ok() {
        return new ResultInfo().success(true).code(ResultCode.R200.getCode()).message(ResultCode.R200.getMsg());
    }

    public static ResultInfo ok(Object payload) {
        return new ResultInfo().success(true).code(ResultCode.R200.getCode()).message(ResultCode.R200.getMsg())
            .payload(payload);
    }

    public static ResultInfo fail() {
        return new ResultInfo().success(false).code(ResultCode.R201.getCode()).message(ResultCode.R201.getMsg());
    }

    public static ResultInfo fail(String message) {
        return new ResultInfo().success(false).message(message).code(ResultCode.R201.getCode());
    }

    public static ResultInfo fail(int code, String message) {
        return new ResultInfo().success(false).message(message).code(code);
    }

    public static ResultInfo notFund() {
        return new ResultInfo().success(false).message(ResultCode.R404.getMsg()).code(ResultCode.R404.getCode());
    }

    public static ResultInfo notFund(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R404.getCode());
    }

    public static ResultInfo methodNotSupported() {
        return new ResultInfo().success(false).message(ResultCode.R405.getMsg()).code(ResultCode.R405.getCode());
    }

    public static ResultInfo methodNotSupported(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R405.getCode());
    }

    public static ResultInfo unavailableService() {
        return new ResultInfo().success(false).message(ResultCode.R503.getMsg()).code(ResultCode.R503.getCode());
    }

    public static ResultInfo unavailableService(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R503.getCode());
    }

    public static ResultInfo unauthorized() {
        return new ResultInfo().success(false).message(ResultCode.R401.getMsg()).code(ResultCode.R401.getCode());
    }

    public static ResultInfo unauthorized(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R401.getCode());
    }

    public static ResultInfo expired() {
        return new ResultInfo().success(false).message(ResultCode.R402.getMsg()).code(ResultCode.R402.getCode());
    }

    public static ResultInfo forbidden() {
        return new ResultInfo().success(false).message(ResultCode.R403.getMsg()).code(ResultCode.R403.getCode());
    }

    public static ResultInfo forbidden(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R403.getCode());
    }

    public static ResultInfo exception() {
        return new ResultInfo().success(false).message(ResultCode.R999.getMsg()).code(ResultCode.R999.getCode());
    }

    public static ResultInfo exception(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R999.getCode());
    }

    public static ResultInfo notLogin(String message) {
        return new ResultInfo().success(false).message(message).code(ResultCode.R202.getCode());
    }

    public static ResultInfo notLogin() {
        return new ResultInfo().success(false).message(ResultCode.R202.getMsg()).code(ResultCode.R202.getCode());
    }

    public static ResultInfo illegalParameter() {
        return new ResultInfo().success(false).message(ResultCode.R104.getMsg()).code(ResultCode.R104.getCode());
    }

    public static ResultInfo illegalParameter(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R104.getCode());
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        return JSON.parseObject(JSON.toJSONString(this));
    }

}