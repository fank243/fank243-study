package com.fank243.study.support.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fank243.study.common.core.constants.RegexConstants;

import lombok.Data;

/**
 * 发送短信
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
public class SmsContentDTO implements Serializable {

    /*** 手机号码 */
    @Pattern(regexp = RegexConstants.MOBILE, message = "请填写正确的手机号码")
    @NotBlank(message = "请填写手机号码")
    private String mobile;

    /*** 短信内容 */
    @NotBlank(message = "短信内容必传")
    private String smsContent;
}
