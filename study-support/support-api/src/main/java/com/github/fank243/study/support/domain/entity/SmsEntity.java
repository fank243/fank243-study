package com.github.fank243.study.support.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fank243.study.core.constants.DateConstant;

import lombok.Data;

/**
 * 短信发送记录表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Data
@TableName("tb_support_sms")
public class SmsEntity {

    @TableId
    private String smsId;

    /*** 消息发送ID */
    private String msgId;

    /*** 手机号码 */
    private String mobile;

    /** 短信内容 */
    private String content;

    /*** 创建时间 */
    @JsonFormat(pattern = DateConstant.YY_MM_DD_HH_MM_SS)
    @TableField(fill = FieldFill.INSERT)
    private Date createdDate;
}
