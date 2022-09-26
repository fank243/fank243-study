package com.fank243.study.api.support.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.api.support.constants.SupportApiConstants;
import com.fank243.study.api.support.domain.dto.ReqRespLogDTO;
import com.fank243.study.api.support.domain.vo.ReqRespLogVO;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.constants.ValidatorGroup;
import com.fank243.study.core.web.exception.BizException;

/**
 * 请求响应日志表 Api
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
public interface IReqRespLogApi {

    /**
     * 请求响应日志表_根据ID查找
     *
     * @param id 主键ID
     * @return 用户实体
     */
    @GetMapping(SupportApiConstants.BASE_URI_SUPPORT_LOG + "/get/{id}")
    ResultInfo<ReqRespLogVO> getById(@PathVariable("id") String id);

    /**
     * 请求响应日志表_分页
     *
     * @param reqRespLog 查询条件
     * @return 列表
     */
    @PostMapping(SupportApiConstants.BASE_URI_SUPPORT_LOG + "/page")
    ResultInfo<PageBean<ReqRespLogVO>> page(@RequestBody ReqRespLogDTO reqRespLog);

    /**
     * 请求响应日志表_新增
     *
     * @param reqRespLog 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(SupportApiConstants.BASE_URI_SUPPORT_LOG + "/add")
    ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) ReqRespLogDTO reqRespLog)
        throws BizException;

    /**
     * 请求响应日志表_删除
     *
     * @param ids 主键ID集合
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @DeleteMapping(SupportApiConstants.BASE_URI_SUPPORT_LOG + "/delete")
    ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;
}
