package com.fank243.study.netty.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Model
 * 
 * @author FanWeiJie
 * @since 2021-05-04 14:12:36
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NettyModel implements Serializable {

    private String msgType;

    private String fromUser;

    private String receiveUser;

    private String message;

}
