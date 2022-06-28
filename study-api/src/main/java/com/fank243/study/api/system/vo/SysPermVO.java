package com.fank243.study.api.system.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * 系统权限表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysPermVO implements Serializable {

    /*** 权限ID */
    private Long permId;

    /*** 权限代码 */
    private String permCode;

    /*** 权限地址 */
    private String permUri;

    /*** 权限名称 */
    private String permName;

    /*** 权限描述 */
    private String permDesc;

    /*** 状态(0：正常，1：禁用) */
    private Integer status;

}
