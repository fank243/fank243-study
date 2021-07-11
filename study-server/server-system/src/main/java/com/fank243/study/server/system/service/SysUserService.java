package com.fank243.study.server.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.ds.utils.BeanUtils;
import com.fank243.study.server.system.dao.ISysUserDao;
import com.fank243.study.server.system.domain.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-15 19:11:16
 */
@Service
public class SysUserService extends ServiceImpl<ISysUserDao, SysUserEntity> {

    @Resource
    private ISysUserDao sysUserDao;

    /**
     * 保存对象
     *
     * @param sysUser 对象实体
     * @return 结果
     */
    public boolean save(SysUserDTO sysUser) {
        return false;
    }

    /**
     * 修改对象
     *
     * @param sysUser 对象实体
     * @return 结果
     */
    public boolean updateById(SysUserDTO sysUser) {
        return false;
    }

    /**
     * 根据ID查找
     *
     * @param id ID
     * @return 对象实体
     */
    public SysUserVO findById(String id) {
        return BeanUtil.toBean(sysUserDao.selectById(id), SysUserVO.class);
    }

    /**
     * 条件查找
     *
     * @param sysUser 对象实体
     * @return 对象实体
     */
    public SysUserVO findByCondition(SysUserDTO sysUser) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotEmpty(sysUser.getUsername()), "username", sysUser.getUsername());
        SysUserEntity sysUserEntity = sysUserDao.selectOne(wrapper);
        return BeanUtil.toBean(sysUserEntity, SysUserVO.class);
    }

    /**
     * 条件查找
     *
     * @param sysUser 对象实体
     * @return 对象实体集合
     */
    public List<SysUserVO> findListByCondition(SysUserDTO sysUser) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotEmpty(sysUser.getUsername()), "username", sysUser.getUsername());
        List<SysUserEntity> sysUserEntityList = sysUserDao.selectList(wrapper);
        return BeanUtil.copyToList(sysUserEntityList, SysUserVO.class);
    }

    /**
     * 分页查找用户实体
     *
     * @param sysUser 参数实体
     * @return 用户对象
     */
    public PageBean<SysUserVO> pageOfUser(SysUserDTO sysUser) {
        IPage<SysUserEntity> page =
            sysUserDao.selectPage(new Page<>(sysUser.getCurrPage(), sysUser.getPageSize()), null);
        return BeanUtils.convert(page, SysUserVO.class);
    }
}
