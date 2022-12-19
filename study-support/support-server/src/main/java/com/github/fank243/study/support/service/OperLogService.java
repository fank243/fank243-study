package com.github.fank243.study.support.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.study.core.domain.dto.OperLogDTO;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.exception.BizException;
import com.github.fank243.study.core.utils.BeanUtils;
import com.github.fank243.study.support.domain.entity.OperLogEntity;
import com.github.fank243.study.support.domain.vo.OperLogVO;
import com.github.fank243.study.support.mapper.IOperLogMapper;

import cn.hutool.core.bean.BeanUtil;

/**
 * 请求响应日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Service
public class OperLogService extends ServiceImpl<IOperLogMapper, OperLogEntity> {

    @Resource
    private IOperLogMapper logMapper;

    /**
     * 请求响应日志表_分页
     *
     * @param reqRespLog 查询条件
     * @return 列表
     */
    public PageBean<OperLogVO> page(OperLogDTO reqRespLog) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<OperLogEntity> wrapper = new QueryWrapper<>();
        IPage<OperLogEntity> page =
            logMapper.selectPage(new Page<>(reqRespLog.getCurrPage(), reqRespLog.getPageSize()), wrapper);
        return BeanUtils.convert(page, OperLogVO.class);
    }

    /**
     * 请求响应日志表_新增
     *
     * @param operLogDTO 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(OperLogDTO operLogDTO) throws BizException {
        OperLogEntity operLogEntity = BeanUtil.toBean(operLogDTO, OperLogEntity.class);
        return save(operLogEntity);
    }

}
