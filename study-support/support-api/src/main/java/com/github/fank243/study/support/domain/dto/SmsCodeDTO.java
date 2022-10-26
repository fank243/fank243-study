package com.github.fank243.study.support.domain.dto;

import java.io.Serializable;

import com.github.fank243.common.pattern.RegexExtPool;
import com.github.fank243.study.core.domain.validator.annotation.Enum;
import com.github.fank243.study.support.constants.smsCodeTypeEnum;

import cn.hutool.core.lang.RegexPool;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = RegexPool.MOBILE, message = "请填写正确的手机号码", groups = {Send.class, Validate.class})
    @NotBlank(message = "请填写手机号码", groups = {Send.class, Validate.class})
    private String mobile;

    /*** 验证码类型 */
    @Enum(clazz = smsCodeTypeEnum.class, message = "验证码类型必传", groups = {Send.class})
    private Integer smsCodeType;

    /*** 消息发送ID */
    @NotBlank(message = "消息发送ID必传", groups = {Validate.class})
    private String msgId;

    /*** 短信验证码 */
    @Pattern(regexp = RegexExtPool.NUMBER_SIX, message = "请填写短信验证码", groups = {Validate.class})
    @NotBlank(message = "请填写短信验证码", groups = {Validate.class})
    private String smsCode;

    public interface Send {}

    public interface Validate {}
}
