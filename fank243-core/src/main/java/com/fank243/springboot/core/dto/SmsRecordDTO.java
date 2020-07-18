package com.fank243.springboot.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 短信发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 23:06:35
 */
@ToString
@Entity
public class SmsRecordDTO implements Serializable {

    @Id
    private Long id;

    private String username;

    private String mobile;

    private String content;

    private String ip;

    @Column(name = "ip_addr")
    private String ipAddr;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_created")
    private Date gmtCreated;
}
