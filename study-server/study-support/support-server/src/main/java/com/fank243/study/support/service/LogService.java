package com.fank243.study.support.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.support.domain.dto.LogDTO;
import com.fank243.study.support.domain.vo.LogVO;
import com.fank243.study.common.core.domain.model.PageBean;
import com.fank243.study.common.core.utils.BeanUtils;
import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.support.mapper.ILogMapper;
import com.fank243.study.support.domain.entity.LogEntity;

import cn.hutool.core.bean.BeanUtil;

/**
 * 请求响应日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Service
public class LogService extends ServiceImpl<ILogMapper, LogEntity> {

    @Resource
    private ILogMapper reqRespLogDao;

    /**
     * 请求响应日志表_分页
     *
     * @param reqRespLog 查询条件
     * @return 列表
     */
    public PageBean<LogVO> page(LogDTO reqRespLog) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<LogEntity> wrapper = new QueryWrapper<>();
        IPage<LogEntity> page =
                reqRespLogDao.selectPage(new Page<>(reqRespLog.getCurrPage(), reqRespLog.getPageSize()), wrapper);
        return BeanUtils.convert(page, LogVO.class);
    }

    /**
     * 请求响应日志表_新增
     *
     * @param reqRespLog 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(LogDTO reqRespLog) throws BizException {
        LogEntity logEntity = BeanUtil.toBean(reqRespLog, LogEntity.class);
        return save(logEntity);
    }

}
