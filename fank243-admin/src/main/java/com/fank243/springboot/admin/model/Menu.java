package com.fank243.springboot.admin.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员权限菜单列表
 * 
 * @author FanWeiJie
 * @date 2020-03-24 11:37:36
 */
@Data
public class Menu implements Serializable {

    private Long id = 0L;

    private Long pid = 0L;

    private String name;

    private String type;

    private String icon = "";

    private String uri = "";

    private int order;

    /** 子菜单 **/
    private List<Menu> list;

    public void setIcon(String icon) {
        this.icon = StringUtils.isBlank(icon) ? "" : icon;
    }

    public void setUri(String uri) {
        this.uri = StringUtils.isBlank(uri) ? "" : uri;
    }
}
