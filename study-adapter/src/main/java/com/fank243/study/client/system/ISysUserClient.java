package com.fank243.study.client.system;

import com.fank243.study.api.system.ISysUserApi;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.core.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-16 21:41:16
 */
@FeignClient(value = "server-system", fallbackFactory = ISysUserClient.SysUserFallbackFactory.class)
public interface ISysUserClient extends ISysUserApi {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Slf4j
    @Component
    class SysUserFallbackFactory implements FallbackFactory<ISysUserClient> {

        public ResultInfo render(Throwable throwable) {
            return ResultInfo.error("系统出错了[" + throwable.getLocalizedMessage() + "]", throwable.getLocalizedMessage());
        }

        @Override
        public ISysUserClient create(Throwable cause) {
            log.error("调用[server-system][系统管理]服务异常：{}", cause.getLocalizedMessage(), cause);

            return new ISysUserClient() {
                @Override
                public ResultInfo<SysUserVO> getById(String id) {
                    return render(cause);
                }

                @Override
                public ResultInfo<PageBean<SysUserVO>> pageOfSysUser(SysUserDTO sysUser) {
                    return render(cause);
                }
            };
        }
    }
}
