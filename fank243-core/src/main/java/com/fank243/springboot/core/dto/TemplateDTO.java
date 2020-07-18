package com.fank243.springboot.core.dto;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 通知模板
 * 
 * @author FanWeiJie
 * @date 2020-03-26 23:06:35
 */
@ToString
@Entity
public class TemplateDTO implements Serializable {

    @Id
    private Long id;
}
