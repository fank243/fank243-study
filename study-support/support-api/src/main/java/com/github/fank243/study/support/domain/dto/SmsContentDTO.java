package com.github.fank243.study.support.domain.dto;

import java.io.Serializable;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Schema(description = "手机号码")
    private String mobile;

    /*** 短信内容 */
    @NotBlank(message = "短信内容必传")
    @Schema(description = "短信内容")
    private String smsContent;
}
