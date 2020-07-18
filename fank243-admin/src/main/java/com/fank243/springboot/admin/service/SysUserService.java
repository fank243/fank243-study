package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.consts.ConfConfig;
import com.fank243.springboot.admin.model.vo.SysUserInfoVO;
import com.fank243.springboot.admin.repository.SysUserRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.service.component.RedisService;
import com.fank243.springboot.admin.service.third.IpAddrService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.RegexUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.consts.RedisKey;
import com.fank243.springboot.core.consts.SysKey;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.entity.SysUser;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fank243.springboot.core.enums.SysUserStatus;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-03-08 12:21:56
 */
@Service
public class SysUserService extends BaseService<SysUser> {
    @Resource
    private SysUserRepository repository;
    @Resource
    private SysUserEventService sysUserEventService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private ConfConfig confConfig;
    @Resource
    private RedisService redisService;
    @Resource
    private IpAddrService ipAddrService;

    /**
     * 根据id查找
     * 
     * @param sysUserId 管理员ID
     * @return Admin
     */
    public SysUser findById(Long sysUserId) {
        return repository.findByIdAndIsDeletedFalse(sysUserId);
    }

    /**
     * 根据用户名查找
     *
     * @param username 用户名
     * @return Admin
     */
    public SysUser findByUsername(String username) {
        return repository.findByUsernameAndIsDeletedFalse(username);
    }

    /**
     * 添加或修改管理员
     * 
     * @param sysUserId 操作员ID
     * @param sysUser {@link SysUser}
     * @param roleIds 角色ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo addOrModify(Long sysUserId, SysUser sysUser, String roleIds) {
        if (!RegexUtils.isUsername(sysUser.getUsername())) {
            return ResultInfo.fail("用户名不合法");
        }
        if (StringUtils.isBlank(sysUser.getRealname())) {
            return ResultInfo.fail("请填写姓名");
        }
        if (!RegexUtils.isMobile(sysUser.getMobile())) {
            return ResultInfo.fail("手机号码不合法");
        }

        List<SysRole> roles = sysRoleService.findByIds(roleIds);
        Set<SysRole> sysRoleSet = new HashSet<>(roles.size());
        sysRoleSet.addAll(roles);
        sysUser.setRoles(sysRoleSet);

        // 添加
        if (sysUser.getId() == null || sysUser.getId() == 0) {
            return add(sysUserId, sysUser);
        }

        // 修改
        return modify(sysUserId, sysUser);
    }

    /**
     * 添加管理员
     *
     * @param sysUserId 管理员ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo add(Long sysUserId, SysUser sysUser) {
        SysUser user = repository.findByUsername(sysUser.getUsername());
        if (user != null) {
            return ResultInfo.fail("用户名已存在");
        }
        user = repository.findByMobile(sysUser.getMobile());
        if (user != null) {
            return ResultInfo.fail("手机号码已存在");
        }
        if (StringUtils.isNotBlank(sysUser.getWxNumber())) {
            user = repository.findByWxNumber(sysUser.getWxNumber());
            if (user != null) {
                return ResultInfo.fail("微信号已存在");
            }
        }

        String salt = StrUtils.randomStr(20);
        String defaultPwd = redisService.hget(RedisKey.SYS_CONFIG, SysKey.DEFAULT_PWD) + "";
        String password = ShiroUtils.md5WithSalt(salt, defaultPwd);
        sysUser.setSalt(salt);
        sysUser.setPassword(password);
        sysUser.setStatus(SysUserStatus.ENABLE);
        sysUser.setIsDeleted(Boolean.FALSE);
        sysUser.setLoginErrCount(0);

        // 持久化
        sysUser = repository.save(sysUser);
        if (sysUser.getId() == null || sysUser.getId() <= 0) {
            return ResultInfo.fail("添加管理员失败");
        }

        String remark = String.format("添加管理员[%s:%s]", sysUser.getId(), sysUser.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ADMIN_MNG, remark);

        return ResultInfo.ok().message("添加管理员成功");
    }

    /**
     * 修改管理员
     *
     * @param sysUserId 管理员ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modify(Long sysUserId, SysUser sysUser) {
        SysUser user = repository.findByUsernameAndIdNot(sysUser.getUsername(), sysUser.getId());
        if (user != null) {
            return ResultInfo.fail("用户名已存在");
        }
        user = repository.findByMobileAndIdNot(sysUser.getMobile(), sysUser.getId());
        if (user != null) {
            return ResultInfo.fail("手机号码已存在");
        }
        if (StringUtils.isNotBlank(sysUser.getWxNumber())) {
            user = repository.findByWxNumberAndIdNot(sysUser.getWxNumber(), sysUser.getId());
            if (user != null) {
                return ResultInfo.fail("微信号已存在");
            }
        }

        SysUser sysUser2 = repository.findByIdAndIsDeletedFalse(sysUser.getId());
        sysUser2.setUsername(sysUser.getUsername());
        sysUser2.setRealname(sysUser.getRealname());
        sysUser2.setMobile(sysUser.getMobile());
        sysUser2.setWxNumber(sysUser.getWxNumber());
        sysUser2.setRoles(sysUser.getRoles());

        // 持久化
        sysUser = repository.save(sysUser2);
        if (sysUser.getId() == null || sysUser.getId() <= 0) {
            return ResultInfo.fail("修改管理员失败");
        }

        // 添加事件
        String remark = String.format("修改管理员[%s:%s]", sysUser.getId(), sysUser.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ADMIN_MNG, remark);

        return ResultInfo.ok().message("修改管理员成功");
    }

    /**
     * 管理员修改个人信息
     *
     * @param sysUserId 管理员ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modifyInfo(Long sysUserId, SysUserInfoVO vo) {
        if (!RegexUtils.isUsername(vo.getUsername())) {
            return ResultInfo.fail("用户名不合法");
        }
        if (StringUtils.isBlank(vo.getRealname())) {
            return ResultInfo.fail("请填写姓名");
        }
        if (!RegexUtils.isMobile(vo.getMobile())) {
            return ResultInfo.fail("手机号码不合法");
        }
        if (StringUtils.isNotBlank(vo.getEmail()) && !RegexUtils.isEmail(vo.getEmail())) {
            return ResultInfo.fail("电子邮箱不合法");
        }
        if (confConfig.getMode().equalsIgnoreCase(IConsts.MODE_PROD)) {
            if (!vo.getIsVerifyMobile()) {
                return ResultInfo.fail("请验证手机号码");
            }
            if (StringUtils.isNotBlank(vo.getEmail()) && !vo.getIsVerifyEmail()) {
                return ResultInfo.fail("请验证电子邮箱");
            }
            if (StringUtils.isBlank(vo.getSmsCode())) {
                return ResultInfo.fail("请填写短信验证码");
            } else {
                Object obj = redisService.hget(RedisKey.SMS_CODE, vo.getMobile());
                if (obj == null) {
                    return ResultInfo.fail("短信验证码已失效，请重新获取");
                }

            }
            if (StringUtils.isNotBlank(vo.getEmail()) && StringUtils.isBlank(vo.getEmailCode())) {
                return ResultInfo.fail("请填写电子邮箱验证码");
            } else {

            }
        }

        Optional<SysUser> optional = repository.findById(vo.getId());
        if (optional.isEmpty()) {
            return ResultInfo.fail("管理员不存在");
        }

        SysUser sysUser = repository.findByUsernameAndIdNot(vo.getUsername(), vo.getId());
        if (sysUser != null) {
            return ResultInfo.fail("用户名已被使用");
        }
        sysUser = repository.findByMobileAndIdNot(vo.getMobile(), vo.getId());
        if (sysUser != null) {
            return ResultInfo.fail("手机号码已被使用");
        }
        if (StringUtils.isNotBlank(vo.getWxNumber())) {
            sysUser = repository.findByWxNumberAndIdNot(vo.getWxNumber(), vo.getId());
            if (sysUser != null) {
                return ResultInfo.fail("微信号已存在");
            }
        }

        sysUser = repository.findByIdAndIsDeletedFalse(vo.getId());
        sysUser.setUsername(vo.getUsername());
        sysUser.setRealname(vo.getRealname());
        sysUser.setMobile(vo.getMobile());
        sysUser.setWxNumber(vo.getWxNumber());
        sysUser.setEmail(vo.getEmail());

        // 持久化
        sysUser = repository.save(sysUser);
        if (sysUser.getId() == null || sysUser.getId() <= 0) {
            return ResultInfo.fail("修改个人信息失败");
        }

        // 添加事件
        String remark = String.format("管理员修改个人信息[%s:%s]", sysUser.getId(), sysUser.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ADMIN_MNG, remark);

        return ResultInfo.ok().message("修改个人信息成功");
    }

    /**
     * 删除管理员
     *
     * @param sysUserId 管理员ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo deleteAdmin(Long sysUserId) {
        if (sysUserId <= 0) {
            return ResultInfo.fail("管理员不存在");
        }

        Optional<SysUser> optionalAdmin = repository.findById(sysUserId);
        if (optionalAdmin.isEmpty()) {
            return ResultInfo.fail("管理员不存在");
        }
        SysUser sysUser = optionalAdmin.get();
        if (sysUser.getIsDeleted()) {
            return ResultInfo.fail("管理员不存在");
        }

        sysUser.setIsDeleted(true);
        Date now = new Date();
        sysUser.setDeletedTime(now);
        sysUser.setGmtModified(now);

        sysUser = repository.save(sysUser);

        if (sysUser.getId() == null || sysUser.getId() <= 0) {
            return ResultInfo.fail("删除管理员失败");
        }

        // 添加事件
        String remark = String.format("删除管理员【%s，%s】", sysUserId, sysUser.getUsername());
        sysUserEventService.addEvent(sysUser.getId(), SysUserEventType.ADMIN_MNG, remark);

        return ResultInfo.ok().message("删除管理员成功");
    }

    /**
     * 修改密码
     *
     * @param sysUserId 管理员ID
     * @param oldPassword 旧密码
     * @param password 登录密码
     * @param confirmPassword 确认密码
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modifyPassword(Long sysUserId, String oldPassword, String password, String confirmPassword) {
        if (sysUserId <= 0) {
            return ResultInfo.fail("管理员不存在");
        }

        Optional<SysUser> optionalAdmin = repository.findById(sysUserId);
        if (optionalAdmin.isEmpty()) {
            return ResultInfo.fail("管理员不存在");
        }

        SysUser sysUser = optionalAdmin.get();
        if (!ShiroUtils.md5WithSalt(sysUser.getSalt(), oldPassword).equals(sysUser.getPassword())) {
            return ResultInfo.fail("旧密码不正确");
        }

        if (!RegexUtils.isPassword(password)) {
            return ResultInfo.fail("密码不合法");
        }
        if (!password.equals(confirmPassword)) {
            return ResultInfo.fail("两次输入的密码不一致");
        }

        sysUser.setId(sysUserId);
        sysUser.setPassword(ShiroUtils.md5WithSalt(sysUser.getSalt(), password));
        sysUser.setGmtModified(new Date());

        sysUser = repository.save(sysUser);
        if (sysUser.getId() == null || sysUser.getId() <= 0) {
            return ResultInfo.fail("修改密码失败");
        }

        // 添加事件
        String remark = String.format("修改密码【%s，%s】", sysUserId, sysUser.getUsername());
        sysUserEventService.addEvent(sysUser.getId(), SysUserEventType.MODIFY_PWD, remark);

        return ResultInfo.ok().message("修改密码成功");
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 登录密码
     * @param imgCode 图形验证码
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo login(String username, String password, String imgCode) {
        if (confConfig.getMode().equalsIgnoreCase(IConsts.MODE_PROD)) {
            if (StringUtils.isBlank(imgCode)) {
                return ResultInfo.fail("请输入图形验证码");
            }
            // TODO FJW 图形验证码
        }
        SysUser sysUser = repository.findByUsernameAndIsDeletedFalse(username);
        if (sysUser == null) {
            return ResultInfo.fail("用户名或密码错误");
        }

        if (!ShiroUtils.md5WithSalt(sysUser.getSalt(), password).equals(sysUser.getPassword())) {
            if (sysUser.getLoginErrCount() + 1 > 5) {
                // 更新状态及锁定时间
                int rows = repository.updateStatusAndLoginLockTime(sysUser.getId(), SysUserStatus.LOCK.getCode(),
                    LocalDateTime.now());
                if (rows <= 0) {
                    return ResultInfo.fail("登录失败，更新数据失败");
                }
            }
            // 更新登录错误次数
            repository.updateLoginErrCount(sysUser.getId());
            return ResultInfo.fail("用户名或密码错误");
        }

        // 锁定时间是否已过
        if (sysUser.getLoginLockTime() != null) {
            if (System.currentTimeMillis() - sysUser.getLoginLockTime().getTime() < 30 * 60 * 1000) {
                return ResultInfo.fail("此账户已被锁定30分钟，请稍后再试");
            }
        }

        if (sysUser.getStatus() == null) {
            return ResultInfo.fail("账号状态非法");
        }
        if (SysUserStatus.DISABLE.getCode() == sysUser.getStatus().getCode()) {
            return ResultInfo.fail("账号已被禁用，请联系管理人员");
        }

        // ip
        String ip = WebUtils.getIp();
        String addr = "";
        IpAddr ipAddr = ipAddrService.getIpAddr(ip);
        if (ipAddr != null) {
            ip = ipAddr.getIp();
            addr = ipAddr.getAddr();
        }

        // 更新登录信息，修改状态、清除登录锁定时间、登录错误次数
        sysUser.setStatus(SysUserStatus.ENABLE);
        sysUser.setLastLoginIp(ip);
        sysUser.setLastLoginIpAddr(addr);
        sysUser.setLoginErrCount(0);
        sysUser.setLoginLockTime(null);
        Date now = new Date();
        sysUser.setLastLoginTime(now);
        sysUser.setGmtModified(now);

        sysUser = repository.save(sysUser);
        if (sysUser.getId() == null || sysUser.getId() <= 0) {
            return ResultInfo.fail("登录失败，更新数据失败");
        }

        // 添加事件
        String remark = String.format("管理员登录【%s，%s】", sysUser.getId(), sysUser.getUsername());
        sysUserEventService.addEvent(sysUser.getId(), SysUserEventType.LOGIN, remark);

        return ResultInfo.ok(sysUser).message("登录成功");
    }

    public PageBean<SysUser> pageOfSysUser(PageInfo pageInfo, String keyword, Integer status) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("select count(*) from tb_sys_user where is_deleted = false");
        querySql.append("select * from tb_sys_user where is_deleted = false");

        Map<String, Object> conditionArgs = new HashMap<>(2);
        if (StringUtils.isNotBlank(keyword)) {
            countSql.append(" and (username like :keyword or realname like :keyword or mobile like :keyword)");
            querySql.append(" and (username like :keyword or realname like :keyword or mobile like :keyword)");
            conditionArgs.put("keyword", "%" + keyword + "%");
        }
        if (SysUserStatus.getEnum(status) != null) {
            countSql.append(" and status =:status");
            querySql.append(" and status =:status");
            conditionArgs.put("status", status);
        }

        return pageOfBySql(pageInfo, countSql.toString(), querySql.toString(), conditionArgs);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modifyStatus(Long sysUserId, Long userId) {
        SysUser sysUser = findById(userId);
        if (sysUser == null) {
            return ResultInfo.fail("管理员不存在");
        }

        SysUserStatus status = sysUser.getStatus().getCode() == SysUserStatus.ENABLE.getCode() ? SysUserStatus.DISABLE
            : SysUserStatus.ENABLE;
        int rows = repository.updateStatusById(userId, status);
        if (rows <= 0) {
            return ResultInfo.fail("修改管理员状态失败");
        }

        // 添加管理员事件
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ADMIN_MNG,
            String.format("修改管理员状态[%s：%s]", sysUser.getId(), sysUser.getRealname()));

        return ResultInfo.ok().message("修改管理员状态成功");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo loginUnlock(Long sysUserId, Long userId) {
        SysUser sysUser = findById(userId);
        if (sysUser == null) {
            return ResultInfo.fail("管理员不存在");
        }

        if (SysUserStatus.LOCK.getCode() != sysUser.getStatus().getCode()) {
            return ResultInfo.fail("管理员未被登录锁定，无需解锁");
        }
        int rows = repository.updateLoginErrorInfoById(userId, SysUserStatus.ENABLE);
        if (rows <= 0) {
            return ResultInfo.fail("解锁管理员登录失败");
        }

        // 添加管理员事件
        String remark = String.format("解锁管理员[%s,%s]登录状态", sysUser.getId(), sysUser.getRealname());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ADMIN_MNG, remark);

        return ResultInfo.ok().message("解锁管理员登录状态成功");
    }

    public ResultInfo resetPassword(Long sysUserId, Long userId) {
        SysUser sysUser = findById(userId);
        if (sysUser == null) {
            return ResultInfo.fail("管理员不存在");
        }

        Object obj = redisService.hget(RedisKey.SYS_CONFIG, SysKey.DEFAULT_PWD);
        if (obj == null) {
            return ResultInfo.fail("获取系统初始密码失败");
        }
        String salt = StrUtils.randomStr(20);
        String password = ShiroUtils.md5WithSalt(salt, obj + "");
        int rows = repository.updatePasswordById(userId, salt, password);
        if (rows <= 0) {
            return ResultInfo.fail("重置管理员登录密码失败");
        }

        // 添加管理员事件
        String remark = String.format("重置管理员[%s,%s]登录密码", sysUser.getId(), sysUser.getRealname());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ADMIN_MNG, remark);

        return ResultInfo.ok().message("重置管理员登录密码成功");

    }

    public ResultInfo delete(Long sysUserId, Long userId) {
        SysUser sysUser = findById(userId);
        if (sysUser == null) {
            return ResultInfo.fail("管理员不存在");
        }

        int rows = repository.updateIsDeletedById(userId);
        if (rows <= 0) {
            return ResultInfo.fail("删除管理员失败");
        }

        // 添加管理员事件
        String remark = String.format("删除管理员[%s,%s]", sysUser.getId(), sysUser.getRealname());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ADMIN_MNG, remark);

        return ResultInfo.ok().message("删除管理员成功");

    }
}
