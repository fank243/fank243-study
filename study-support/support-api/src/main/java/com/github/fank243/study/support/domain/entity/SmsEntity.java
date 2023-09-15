package com.github.fank243.study.support.domain.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fank243.study.core.constants.DateConstants;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 短信发送记录表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Getter
@Setter
@SuperBuilder
@Table("tb_support_sms")
public class SmsEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String smsId;

    /*** 消息发送ID */
    private String msgId;

    /*** 手机号码 */
    private String mobile;

    /** 短信内容 */
    private String content;

    /*** 创建时间 */
    @JsonFormat(pattern = DateConstants.YY_MM_DD_HH_MM_SS)
    // @TableField(fill = FieldFill.INSERT)
    private Date createdDate;
}
