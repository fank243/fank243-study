package com.github.fank243.study.core.web.interceptor;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

import com.github.fank243.study.core.annotation.RepeatSubmit;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;

/**
 * 重复提交拦截器：基于请求参数
 * 
 * @author FanWeiJie
 * @since 2022-06-10 10:10:49
 */
@SuppressWarnings("unchecked")
@Component
@ConditionalOnWebApplication(type = SERVLET)
public class RepeatSubmitInterceptor extends AbstractRepeatSubmitInterceptor {

    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();
        String body = SecureUtil.md5(JSONUtil.toJsonStr(ServletUtil.getParamMap(request)));

        Object obj = session.getAttribute("repeatSubmitParam");
        if (obj != null) {
            Map<String, Object> sessionMap = (Map<String, Object>)obj;
            if (sessionMap.containsKey(uri)) {
                Map<String, Object> dataMap = (Map<String, Object>)sessionMap.get(uri);
                String param = (String)dataMap.getOrDefault("param", "");
                long time = Convert.toLong(dataMap.get("time"), 0L);

                if (param.equalsIgnoreCase(body) && System.currentTimeMillis() - time < annotation.interval()) {
                    return Boolean.TRUE;
                }
            }
        }

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("param", body);
        dataMap.put("time", System.currentTimeMillis());

        Map<String, Object> sessionMap = new HashMap<>(1);
        sessionMap.put(uri, dataMap);

        session.setAttribute("repeatSubmitParam", sessionMap);
        return Boolean.FALSE;
    }
}
