package com.github.fank243.study.support.domain.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 短信验证码
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
public class SmsCodeVO implements Serializable {

    /*** 手机号码 */
    private String mobile;

    /*** 消息发送ID */
    private String msgId;
}
