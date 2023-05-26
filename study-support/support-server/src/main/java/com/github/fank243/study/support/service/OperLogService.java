package com.github.fank243.study.support.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.study.core.domain.dto.OperLogDTO;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.utils.BeanUtils;
import com.github.fank243.study.log.OperLogEntity;
import com.github.fank243.study.log.mapper.IOperLogMapper;
import com.github.fank243.study.support.domain.vo.OperLogVO;

import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;

/**
 * 请求响应日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Service
public class OperLogService extends ServiceImpl<IOperLogMapper, OperLogEntity> {

    @Resource
    private IOperLogMapper operLogMapper;

    /**
     * 请求响应日志表_分页
     *
     * @param operLogDTO 查询条件
     * @return 列表
     */
    public PageBean<OperLogVO> page(OperLogDTO operLogDTO) {
        QueryWrapper<OperLogEntity> wrapper = new QueryWrapper<>();
        wrapper.setEntity(BeanUtil.copyProperties(operLogDTO, OperLogEntity.class));
        wrapper.orderByDesc("create_time");
        IPage<OperLogEntity> page =
            operLogMapper.selectPage(new Page<>(operLogDTO.getCurrPage(), operLogDTO.getPageSize()), wrapper);
        return BeanUtils.convert(page, OperLogVO.class);
    }
}
