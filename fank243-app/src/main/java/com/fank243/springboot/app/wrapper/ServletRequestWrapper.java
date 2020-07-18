package com.fank243.springboot.app.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * 修改请求参数
 * 
 * @author FanWeiJie
 * @date 2019-11-27 14:06:32
 */
public class ServletRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = new HashMap<>();

    public ServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 重载一个构造方法
     * 
     * @param request HttpServletRequest
     * @param extendParams 请求参数
     */
    public ServletRequestWrapper(HttpServletRequest request, Map<String, String[]> extendParams) {
        this(request);
        addAllParameters(extendParams);
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> enumeration = super.getParameterNames();
        ArrayList<String> list = Collections.list(enumeration);
        list.add("roleTypes");
        return Collections.enumeration(list);
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public void addAllParameters(Map<String, String[]> otherParams) {
        for (Map.Entry<String, String[]> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[])value);
            } else if (value instanceof String) {
                params.put(name, new String[] {(String)value});
            } else {
                params.put(name, new String[] {String.valueOf(value)});
            }
        }
    }
}