package com.github.fank243.study.support.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 发送短信
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Schema(description = "短信请求实体")
@Data
public class SmsContentDTO implements Serializable {

    /*** 手机号码 */
    @Pattern(regexp = RegexPool.MOBILE, message = "请填写正确的手机号码")
    @NotBlank(message = "请填写手机号码")
    @Schema(description = "手机号码", required = true)
    private String mobile;

    /*** 短信内容 */
    @NotBlank(message = "短信内容必传")
    @Schema(description = "短信内容", required = true)
    private String smsContent;
}
