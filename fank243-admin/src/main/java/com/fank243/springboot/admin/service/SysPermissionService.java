package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.model.Menu;
import com.fank243.springboot.admin.model.vo.SysPermissionVO;
import com.fank243.springboot.admin.repository.SysPermissionRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.dto.SysPermissionDTO;
import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.entity.SysUser;
import com.fank243.springboot.core.enums.PermissionType;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 权限
 * 
 * @author FanWeiJie
 * @date 2020-03-08 12:21:56
 */
@Service
public class SysPermissionService extends BaseService<SysPermission> {
    @Resource
    private SysPermissionRepository repository;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserEventService sysUserEventService;

    /**
     * 根据ID查找
     *
     * @param permissionId 权限ID
     * @return Admin
     */
    public Optional<SysPermission> findById(Long permissionId) {
        return repository.findById(permissionId);
    }

    /**
     * 根据管理员ID查找
     * 
     * @param sysUserId 管理员iD
     * @return 权限列表
     */
    public List<SysPermission> findBySysUserId(Long sysUserId) {
        SysUser sysUser = sysUserService.findById(sysUserId);
        List<SysPermission> sysPermissions = new ArrayList<>();
        if (sysUser.getRoles().size() > 0) {
            for (SysRole sysRole : sysUser.getRoles()) {
                if (sysRole.getPermissions().size() > 0) {
                    sysPermissions.addAll(sysRole.getPermissions());
                }
            }
        }
        return sysPermissions;
    }

    /**
     * 添加
     * 
     * @param sysUserId 管理员ID
     * @param vo {@link SysPermissionVO}
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo add(Long sysUserId, SysPermissionVO vo) {
        SysPermission sysPermission = new SysPermission();
        if (vo.getTpid() > 0) {
            sysPermission.setPid(vo.getTpid());
        } else if (vo.getSpid() > 0) {
            sysPermission.setPid(vo.getSpid());
        }

        if (vo.getPid() > 0) {
            Optional<SysPermission> optionalRight = repository.findById(vo.getPid());
            if (optionalRight.isEmpty()) {
                return ResultInfo.fail("父权限不存在");
            }
        }

        sysPermission = new SysPermission();
        sysPermission.setPid(vo.getPid());
        sysPermission.setName(vo.getName());
        sysPermission.setExternal(vo.getExternal());
        sysPermission.setPermission(vo.getPermission());
        sysPermission.setUri(vo.getUri());
        sysPermission.setSort(vo.getSort());
        sysPermission.setIcon(vo.getIcon());
        sysPermission.setType(vo.getType());
        sysPermission.setAvailable(Boolean.TRUE);

        sysPermission = repository.save(sysPermission);
        if (sysPermission.getId() == null || sysPermission.getId() <= 0) {
            return ResultInfo.fail("添加权限失败");
        }

        // 添加事件
        String remark = String.format("添加权限[%s:%s]", sysPermission.getId(), sysPermission.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.PERM_MNG, remark);

        return ResultInfo.ok().message("添加权限成功");
    }

    /**
     * 修改
     * 
     * @param sysUserId 管理员ID
     * @param vo {@link SysPermissionVO}
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modify(Long sysUserId, SysPermissionVO vo) {
        Optional<SysPermission> optional = repository.findById(vo.getId());
        if (optional.isEmpty()) {
            return ResultInfo.fail("权限不存在");
        }

        SysPermission sysPermission = optional.get();
        // 自身为一级菜单时，必须设置为菜单类型，并且不可为外部链接
        sysPermission.setType(PermissionType.MENU);
        sysPermission.setExternal(false);

        // 自身为一级菜单
        long pid = 0;
        // 父菜单 > 三级菜单
        if (vo.getTpid() > 0 && vo.getTpid().longValue() != vo.getId()) {
            pid = vo.getTpid();
            sysPermission.setType(vo.getType());
            sysPermission.setExternal(vo.getExternal());
        }
        // 父菜单 > 二级菜单
        else if (vo.getSpid() > 0 && vo.getSpid().longValue() != vo.getId()) {
            pid = vo.getSpid();
            sysPermission.setType(vo.getType());
            sysPermission.setExternal(vo.getExternal());
        }
        // 父菜单 > 一级菜单
        else if (vo.getPid() > 0 && vo.getPid().longValue() != vo.getId()) {
            pid = vo.getPid();
            sysPermission.setType(vo.getType());
            sysPermission.setExternal(vo.getExternal());
        }

        if (pid > 0) {
            Optional<SysPermission> optionalRight = repository.findById(vo.getPid());
            if (optionalRight.isEmpty()) {
                return ResultInfo.fail("父权限不存在");
            }
        }

        // 不能设置为菜单
        if (vo.getTpid() > 0 && vo.getTpid().longValue() == vo.getId()) {
            sysPermission.setType(PermissionType.BUTTON);
        }

        sysPermission.setPid(pid);
        sysPermission.setName(vo.getName());
        sysPermission.setPermission(vo.getPermission());
        sysPermission.setUri(vo.getUri());
        sysPermission.setSort(vo.getSort());
        sysPermission.setIcon(vo.getIcon());
        sysPermission.setGmtModified(new Date());

        sysPermission = repository.save(sysPermission);
        if (sysPermission.getId() == null || sysPermission.getId() <= 0) {
            return ResultInfo.fail("修改权限失败");
        }

        // 添加事件
        String remark = String.format("修改权限[%s:%s]", sysPermission.getId(), sysPermission.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.PERM_MNG, remark);

        return ResultInfo.ok().message("修改权限成功");
    }

    /**
     * 修改权限状态
     *
     * @param sysUserId 管理员ID
     * @param permissionId 权限ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modifyStatus(Long sysUserId, Long permissionId) {
        Optional<SysPermission> optional = repository.findById(permissionId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("权限不存在");
        }
        SysPermission sysPermission = optional.get();

        boolean available = !sysPermission.getAvailable();
        sysPermission.setAvailable(available);
        sysPermission.setGmtModified(new Date());

        sysPermission = repository.save(sysPermission);
        if (sysPermission.getId() == null || sysPermission.getId() <= 0) {
            return ResultInfo.fail("修改权限状态失败");
        }

        // 添加事件
        String remark = String.format("修改权限状态【%s：%s】", permissionId, sysPermission.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.PERM_MNG, remark);

        return ResultInfo.ok().message("修改权限状态成功");
    }

    /**
     * 删除权限
     * 
     * @param sysUserId 管理员ID
     * @param permissionId 权限ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo delete(Long sysUserId, Long permissionId) {
        Optional<SysPermission> optional = repository.findById(permissionId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("权限不存在");
        }
        SysPermission sysPermission = optional.get();

        // 物理删除
        repository.deleteById(permissionId);

        // 添加事件
        String remark = String.format("删除权限【%s：%s】", permissionId, sysPermission.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.PERM_MNG, remark);

        return ResultInfo.ok().message("删除权限成功");
    }

    public PageBean<SysPermissionDTO> pageOfPermission(PageInfo pageInfo, Long pid, String keyword) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT COUNT(*) FROM tb_sys_permission WHERE 1=1");
        querySql.append(
            "SELECT *,(select name from tb_sys_permission where id = a.pid) as pid_name FROM tb_sys_permission a WHERE 1=1");

        Map<String, Object> conditionArgs = new HashMap<>(2);
        if (pid > 0) {
            countSql.append(" AND pid =:pid");
            querySql.append(" AND pid =:pid");
            conditionArgs.put("pid", pid);
        }
        if (StringUtils.isNotBlank(keyword)) {
            countSql.append(" AND (name like :keyword or permission like :keyword or uri like :keyword)");
            querySql.append(" AND (name like :keyword or permission like :keyword or uri like :keyword)");
            conditionArgs.put("keyword", keyword);
        }
        querySql.append(" ORDER BY a.pid,a.sort,a.gmt_modified DESC,a.id DESC");

        return pageOfBeanBySql(pageInfo, countSql.toString(), querySql.toString(), SysPermissionDTO.class,
            conditionArgs);
    }

    public List<SysPermission> findByPid(long pid) {
        return repository.findByPidAndAvailableIsTrue(pid);
    }

    /**
     * 递归查询父菜单
     * 
     * @param list 父菜单ID
     * @param permission 权限
     */
    public void findAllPid(List<Long> list, SysPermission permission) {
        list.add(permission.getPid());
        if (permission.getPid() <= 0) {
            return;
        }

        Optional<SysPermission> optional = repository.findById(permission.getPid());
        if (optional.isPresent()) {
            permission = optional.get();
            findAllPid(list, permission);
        }
    }

    public List<SysPermission> findByIds(String permIds) {
        String[] split = permIds.split(",");
        List<Long> ids = new ArrayList<>(split.length);
        for (String str : split) {
            ids.add(Long.valueOf(str));
        }
        return repository.findByIdIn(ids);
    }

    /**
     * 管理员菜单 > 递归查找所有权限菜单，并默认选中该管理员拥有的菜单
     * 
     * @param sysUserId 用户ID
     * @return 菜单列表
     */
    public List<Menu> findPermissionBySysUserId(long sysUserId) {
        List<SysPermission> sysPermissionList = findBySysUserId(sysUserId);
        if (sysPermissionList == null) {
            return new ArrayList<>(1);
        }

        List<Long> permIdList = new ArrayList<>(sysPermissionList.size());
        for (SysPermission permission : sysPermissionList) {
            permIdList.add(permission.getId());
        }

        // 查询所有菜单
        List<SysPermission> permissions = repository.findByTypeAndAvailableTrue(PermissionType.MENU);
        // 根节点
        List<Menu> rootMenu = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if (permission.getPid() == 0 && permIdList.contains(permission.getId())) {
                Menu menu = new Menu();
                menu.setId(permission.getId());
                menu.setPid(permission.getPid());
                menu.setName(permission.getName());
                menu.setType(permission.getType().name());
                menu.setUri(permission.getUri());
                menu.setIcon(permission.getIcon());
                menu.setOrder(permission.getSort());
                rootMenu.add(menu);
            }
        }
        rootMenu.sort(order());
        for (Menu nav : rootMenu) {
            List<Menu> childList = findPermissionBySysUserId(nav.getId(), permIdList, permissions);
            nav.setList(childList);
        }

        return rootMenu;
    }

    /**
     * 管理员菜单 > 递归查找所有权限菜单，并默认选中该管理员拥有的菜单
     * 
     * @param pid 父节点id
     * @param permissions 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<Menu> findPermissionBySysUserId(long pid, List<Long> permIdList, List<SysPermission> permissions) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if (permission.getPid() == pid && permIdList.contains(permission.getId())) {
                Menu menu = new Menu();
                menu.setId(permission.getId());
                menu.setPid(permission.getPid());
                menu.setName(permission.getName());
                menu.setType(permission.getType().name());
                menu.setUri(permission.getUri());
                menu.setIcon(permission.getIcon());
                menu.setOrder(permission.getSort());
                childList.add(menu);
            }
        }
        // 递归
        for (Menu nav : childList) {
            nav.setList(findPermissionBySysUserId(nav.getId(), permIdList, permissions));
        }

        childList.sort(order());

        // 如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<>(1);
        }
        return childList;
    }

    /**
     * 排序,根据order排序
     */
    public Comparator<Menu> order() {
        return (o1, o2) -> {
            if (o1.getOrder() != o2.getOrder()) {
                return o1.getOrder() - o2.getOrder();
            }
            return 0;
        };
    }

    /**
     * 递归查找所有权限菜单
     * 
     * @return 权限菜单
     */
    public List<Menu> findPermission() {
        // 查询所有菜单
        List<SysPermission> permissions = repository.findByAvailableTrue();
        // 根节点
        List<Menu> rootMenu = new ArrayList<>();
        for (SysPermission nav : permissions) {
            Menu menu = new Menu();
            menu.setId(nav.getId());
            menu.setPid(nav.getPid());
            menu.setOrder(nav.getSort());
            menu.setName(nav.getName());
            menu.setIcon(nav.getIcon());
            menu.setUri(nav.getUri());
            rootMenu.add(menu);
        }
        rootMenu.sort(order());
        return rootMenu;
    }

    /**
     * 角色权限 > 递归查找所有权限菜单
     *
     * @param pid 父节点id
     * @param permissions 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    private List<Menu> findPermissionChildren(long pid, List<SysPermission> permissions) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if (permission.getPid() == pid) {
                Menu menu = new Menu();
                menu.setId(permission.getId());
                menu.setPid(permission.getPid());
                menu.setOrder(permission.getSort());
                menu.setName(permission.getName());
                menu.setIcon(permission.getIcon());
                menu.setUri(permission.getUri());
                childList.add(menu);
            }
        }
        // 递归
        for (Menu nav : childList) {
            nav.setList(findPermissionChildren(nav.getId(), permissions));
        }

        childList.sort(order());

        // 如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<>(1);
        }
        return childList;
    }

}
