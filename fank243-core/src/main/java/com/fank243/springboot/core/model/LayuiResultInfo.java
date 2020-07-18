package com.fank243.springboot.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Layui 响应结果集
 * 
 * @author FanWeiJie
 * @date 2019-10-29 13:58:22
 */
@Data
public class LayuiResultInfo implements Serializable {

    /** 响应码，0：成功，1：失败 **/
    private int code;

    /** 响应消息 **/
    private String msg;

    /** 总记录数 **/
    private long count;

    /** 数据包 **/
    private Object data;

    public LayuiResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayuiResultInfo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public LayuiResultInfo(long count, Object data) {
        this.code = 0;
        this.msg = "";
        this.count = count;
        this.data = data;
    }
}
