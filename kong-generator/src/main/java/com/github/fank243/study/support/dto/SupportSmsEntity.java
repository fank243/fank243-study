package com.github.fank243.kong.support.dto;

import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.ColumnMask;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 短信发送记录表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:11
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_support_sms", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SupportSmsEntity extends BaseEntity implements Serializable {

    /** 短信ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long smsId;

    /** 消息ID */
    private String msgId;

    /** 接受手机号码 */
    @ColumnMask("mobile")
    private String mobile;

    /** 短信内容 */
    private String content;

}
