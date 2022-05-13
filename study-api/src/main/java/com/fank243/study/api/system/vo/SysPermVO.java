package com.fank243.study.api.system.vo;

import java.io.Serializable;
import lombok.Data;

/**
* 系统权限表
*
* @author FanWeiJie
* @since 2022-05-13
*/
@Data
public class SysPermVO implements Serializable {


    /*** 主键ID */
    private String permId;

    /*** 权限代码 */
    private String permCode;

    /*** 权限地址 */
    private String permUri;

    /*** 权限描述 */
    private String permDesc;


}
