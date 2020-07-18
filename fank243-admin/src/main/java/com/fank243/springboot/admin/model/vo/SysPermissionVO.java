package com.fank243.springboot.admin.model.vo;

import com.fank243.springboot.admin.model.Update;
import com.fank243.springboot.core.enums.PermissionType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 权限
 * 
 * @author FanWeiJie
 * @date 2020-03-29 01:32:40
 */
@Data
public class SysPermissionVO {

    @Min(value = 1, message = "权限ID不存在", groups = Update.class)
    @NotNull(message = "权限ID不能为空", groups = Update.class)
    private Long id;

    @NotNull(message = "请选择父菜单")
    private Long pid;

    @NotNull(message = "请选择二级菜单")
    private Long spid;

    @NotNull(message = "请选择三级级菜单")
    private Long tpid;

    @NotEmpty(message = "请填写权限名称")
    private String name;

    @NotNull(message = "请选择权限类型")
    private PermissionType type;

    private String permission;

    @NotNull(message = "请选择是否外部链接")
    private Boolean external;

    private String uri;

    private String icon;

    @NotNull(message = "请填写权限序号")
    private Integer sort;

    public void setType(String type) {
        this.type = PermissionType.getEnum(type);
    }

}
