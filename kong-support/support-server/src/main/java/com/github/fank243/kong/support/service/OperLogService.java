/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.support.service;

import org.springframework.stereotype.Service;

import com.github.fank243.kong.core.domain.dto.OperLogDTO;
import com.github.fank243.kong.core.domain.model.PageBean;
import com.github.fank243.kong.support.domain.vo.OperLogVO;

/**
 * 请求响应日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Service
public class OperLogService {
//
//    @Resource
//    private IOperLogRepository operLogRepository;

    /**
     * 请求响应日志表_分页
     *
     * @param operLogDTO 查询条件
     * @return 列表
     */
    public PageBean<OperLogVO> page(OperLogDTO operLogDTO) {
        //
        // Page<OperLogEntity> operLogEntityPage =
        // operLogMapper.paginate(new Page<>(operLogDTO.getCurrPage(), operLogDTO.getPageSize()), queryWrapper);
        //
        // OperLogEntity operLogEntity = BeanUtil.copyProperties(operLogDTO, OperLogEntity.class);
        // QueryWrapper.create(operLogEntity);
        //
        // return BeanUtils.convert(operLogEntityPage, OperLogVO.class);
        return null;
    }
}
