package com.fank243.springboot.admin.consts;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取配置文件
 *
 * @author FanWeiJie
 * @date 2019-10-25 16:19:16
 */
@Getter
@Component
public class ConfConfig {

    @Value(value = "${spring.profiles.active}")
    private String mode;

}
