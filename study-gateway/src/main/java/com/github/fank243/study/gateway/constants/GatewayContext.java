package com.github.fank243.study.gateway.constants;

import java.io.Serializable;

import org.springframework.data.repository.query.parser.Part;
import org.springframework.util.MultiValueMap;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * 上下文对象
 * 
 * @author FanWeiJie
 * @since 2022-03-11 14:57:36
 */
@Data
public class GatewayContext implements Serializable {

    public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";

    /**
     * cache json body
     */
    private String cacheBody;
    /**
     * cache form data
     */
    private MultiValueMap<String, Part> formData;
    /**
     * cache request path
     */
    private String path;

    public String getCacheBody() {
        return StrUtil.isNotBlank(this.cacheBody) ? JSONUtil.toJsonStr(JSONUtil.parseObj(this.cacheBody)) : "{}";
    }
}
