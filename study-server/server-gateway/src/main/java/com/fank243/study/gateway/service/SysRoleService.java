package com.fank243.study.gateway.service;

import com.fank243.study.gateway.dao.SysRoleDao;
import com.fank243.study.gateway.entity.SysRoleEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统角色
 * 
 * @author FanWeiJie
 * @since 2022-05-11 14:03:17
 */
@Service
public class SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    public List<SysRoleEntity> findByUserId(String userId){
        return sysRoleDao.findByUserId(userId);
    }
}
