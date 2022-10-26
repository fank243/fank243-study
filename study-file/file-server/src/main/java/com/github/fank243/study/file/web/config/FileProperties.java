package com.github.fank243.study.file.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 文件服务属性
 * 
 * @author FanWeiJie
 * @since 2022-09-28 16:02:39
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "study.file")
public class FileProperties {

    /** 文件上传根路径 **/
    private String path;
}
