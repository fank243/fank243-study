package com.fank243.springboot.core.dto;

import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户事件
 * 
 * @author FanWeiJie
 * @date 2020-03-25 22:52:01
 */
@Data
@Entity
public class UserEventDTO implements Serializable {

    @Id
    private Long id;

    private String username;

    private String type;

    @Enumerated
    @Column(name = "device_type")
    private DeviceType deviceType;

    @Column(name = "device_number")
    private String deviceNumber;

    private String ip;

    @Column(name = "ip_addr")
    private String ipAddr;

    private String remark;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_created")
    private Date gmtCreated;

    public String getType() {
        SysUserEventType eventType = SysUserEventType.getEnum(type);
        if (eventType == null) {
            return "未知";
        }
        return eventType.getDesc();
    }

}
