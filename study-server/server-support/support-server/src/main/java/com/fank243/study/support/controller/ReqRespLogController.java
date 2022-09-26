package com.fank243.study.support.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.support.api.IReqRespLogApi;
import com.fank243.study.api.support.domain.dto.ReqRespLogDTO;
import com.fank243.study.api.support.domain.vo.ReqRespLogVO;
import com.fank243.study.common.domain.base.BaseController;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.web.exception.BizException;
import com.fank243.study.support.domain.entity.ReqRespLogEntity;
import com.fank243.study.support.service.ReqRespLogService;

import cn.hutool.core.bean.BeanUtil;

/**
 * 请求响应日志表 控制器
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@RestController
public class ReqRespLogController extends BaseController implements IReqRespLogApi {

    @Resource
    private ReqRespLogService reqRespLogService;

    @Override
    public ResultInfo<ReqRespLogVO> getById(String id) {
        ReqRespLogEntity reqRespLog = reqRespLogService.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(reqRespLog, ReqRespLogVO.class));
    }

    @Override
    public ResultInfo<PageBean<ReqRespLogVO>> page(@RequestBody ReqRespLogDTO reqRespLog) {
        return ResultInfo.ok(reqRespLogService.page(reqRespLog));
    }

    @Override
    public ResultInfo<?> add(ReqRespLogDTO reqRespLog) throws BizException {
        boolean isOk = reqRespLogService.add(reqRespLog);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
    }

    @Override
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        boolean isOk = reqRespLogService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.fail("删除失败");
    }
}
