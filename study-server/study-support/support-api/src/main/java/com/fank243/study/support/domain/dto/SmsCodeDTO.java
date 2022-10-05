package com.fank243.study.support.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fank243.study.common.core.constants.RegexConstants;
import com.fank243.study.common.core.domain.validator.annotation.Enum;
import com.fank243.study.support.constants.smsCodeTypeEnum;

import lombok.Data;

/**
 * 短信验证码
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
public class SmsCodeDTO implements Serializable {

    /*** 手机号码 */
    @Pattern(regexp = RegexConstants.MOBILE, message = "请填写正确的手机号码", groups = {Send.class, Validate.class})
    @NotBlank(message = "请填写手机号码", groups = {Send.class, Validate.class})
    private String mobile;

    /*** 验证码类型 */
    @Enum(clazz = smsCodeTypeEnum.class, message = "验证码类型必传", groups = {Send.class})
    private Integer smsCodeType;

    /*** 消息发送ID */
    @NotBlank(message = "消息发送ID必传", groups = {Validate.class})
    private String msgId;

    /*** 短信验证码 */
    @Pattern(regexp = RegexConstants.NUMBER_SIX,message = "请填写短信验证码", groups = {Validate.class})
    @NotBlank(message = "请填写短信验证码", groups = {Validate.class})
    private String smsCode;

    public interface Send {}

    public interface Validate {}
}
