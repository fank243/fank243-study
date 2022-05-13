package com.fank243.study.api.system.client;

import com.fank243.study.api.system.api.ISysRoleApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 系统角色表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(value = "server-system")
public interface ISysRoleFeignClient extends ISysRoleApi {

}
