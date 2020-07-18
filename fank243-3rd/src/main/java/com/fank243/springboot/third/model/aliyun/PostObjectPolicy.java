package com.fank243.springboot.third.model.aliyun;

import lombok.Data;

/**
 * OSS Post object
 * 
 * @author FanWeiJie
 * @date 2019-11-08 15:57:23
 */
@Data
public class PostObjectPolicy {

    /** AccessID **/
    private String accessId;

    /** 调用地址 **/
    private String host;

    /** 文件存储目录 **/
    private String dir;

    /**  **/
    private String policy;

    /** 过期事件，单位：s **/
    private String expire;

    /** 签名 **/
    private String signature;
}