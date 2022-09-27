package com.fank243.study.support.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.support.domain.dto.ReqRespLogDTO;
import com.fank243.study.support.domain.vo.ReqRespLogVO;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.BeanUtils;
import com.fank243.study.core.web.exception.BizException;
import com.fank243.study.support.dao.IReqRespLogDao;
import com.fank243.study.support.domain.entity.ReqRespLogEntity;

import cn.hutool.core.bean.BeanUtil;

/**
 * 请求响应日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Service
public class ReqRespLogService extends ServiceImpl<IReqRespLogDao, ReqRespLogEntity> {

    @Resource
    private IReqRespLogDao reqRespLogDao;

    /**
     * 请求响应日志表_分页
     *
     * @param reqRespLog 查询条件
     * @return 列表
     */
    public PageBean<ReqRespLogVO> page(ReqRespLogDTO reqRespLog) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<ReqRespLogEntity> wrapper = new QueryWrapper<>();
        IPage<ReqRespLogEntity> page =
                reqRespLogDao.selectPage(new Page<>(reqRespLog.getCurrPage(), reqRespLog.getPageSize()), wrapper);
        return BeanUtils.convert(page, ReqRespLogVO.class);
    }

    /**
     * 请求响应日志表_新增
     *
     * @param reqRespLog 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(ReqRespLogDTO reqRespLog) throws BizException {
        ReqRespLogEntity reqRespLogEntity = BeanUtil.toBean(reqRespLog, ReqRespLogEntity.class);
        return save(reqRespLogEntity);
    }

}
