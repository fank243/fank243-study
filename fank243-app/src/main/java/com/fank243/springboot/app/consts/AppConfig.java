package com.fank243.springboot.app.consts;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统配置
 * 
 * @author FanWeiJie
 * @date 2019-11-26 16:55:08
 */
@Data
@Component
@ConfigurationProperties(prefix = "jboot.app")
public class AppConfig {

    /** swagger ui **/
    private Boolean swagger;

    /** RSA 私钥 **/
    private String rsaPri;
    /** RSA 公钥 **/
    private String rsaPub;

}
