package com.fank243.study.support.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信验证码
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
public class SmsVO implements Serializable {

    private String smsId;

    /*** 消息发送ID */
    private String msgId;

    /*** 手机号码 */
    private String mobile;

    /** 短信内容 */
    private String content;

    /*** 创建时间 */
    private String createdDate;
}
