package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.model.vo.UserVO;
import com.fank243.springboot.admin.repository.UserRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.redis.RedisKey;
import com.fank243.springboot.common.utils.CacheUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.DictConsts;
import com.fank243.springboot.core.consts.SysKey;
import com.fank243.springboot.core.entity.User;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fank243.springboot.core.exception.BizException;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 用户
 * 
 * @author FanWeiJie
 * @date 2020-03-25 23:41:32
 */
@SuppressWarnings("ConstantConditions")
@Service
public class UserService extends BaseService<User> {

    @Resource
    private UserRepository repository;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SysUserEventService sysUserEventService;

    public PageBean<User> pageOfUser(PageInfo pageInfo, String keyword, Integer status) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT COUNT(*) FROM tb_user WHERE is_deleted = false");
        querySql.append("SELECT * FROM tb_user WHERE is_deleted = false");

        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(keyword)) {
            countSql.append(" AND (username like :keyword or mobile like :keyword)");
            querySql.append(" AND (username like :keyword or mobile like :keyword)");
            conditionArgs.put("keyword", "%" + keyword + "%");
        }
        if (status >= 0) {
            countSql.append(" AND status =:status");
            querySql.append(" AND status =:status");
            conditionArgs.put("status", status);
        }
        querySql.append(" ORDER BY id DESC");

        return pageOfBySql(pageInfo, countSql.toString(), querySql.toString(), conditionArgs);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo add(Long sysUserId, UserVO vo) throws BizException {
        User user = repository.findByUsername(vo.getUsername());
        if (user != null) {
            return ResultInfo.fail("用户名已被使用");
        }
        user = repository.findByMobile(vo.getMobile());
        if (user != null) {
            return ResultInfo.fail("手机号码已被使用");
        }

        String defaultPwd = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.DEFAULT_PWD) + "";
        String salt = StrUtils.randomStr(20);
        String password = ShiroUtils.md5WithSalt(salt, defaultPwd);

        user = new User();
        user.setUsername(vo.getUsername());
        user.setMobile(vo.getMobile());
        user.setNickname(vo.getNickname());
        user.setSalt(salt);
        user.setPassword(password);
        user.setIsDeleted(false);
        user.setStatus(vo.getStatus());
        user.setLoginErrCount(0);

        user = repository.save(user);
        if (user.getId() == null || user.getId() <= 0) {
            return ResultInfo.fail("添加用户失败");
        }

        // 同步添加UserInfo
        ResultInfo result = userInfoService.addUserInfo(user);
        if (!result.isSuccess()) {
            throw new BizException(result);
        }

        // 添加事件
        String remark = String.format("添加[%s:%s]", user.getId(), user.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.USER_MNG, remark);

        return ResultInfo.ok().message("添加用户成功");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modify(Long sysUserId, UserVO vo) throws BizException {
        Optional<User> optional = repository.findById(vo.getId());
        if (optional.isEmpty()) {
            return ResultInfo.fail("用户不存在");
        }
        User user = optional.get();

        User vUser = repository.findByUsernameAndIdNot(vo.getUsername(), vo.getId());
        if (vUser != null) {
            return ResultInfo.fail("用户名已被使用");
        }
        vUser = repository.findByMobileAndIdNot(vo.getMobile(), vo.getId());
        if (vUser != null) {
            return ResultInfo.fail("手机号码已被使用");
        }

        user.setUsername(vo.getUsername());
        user.setMobile(vo.getMobile());
        user.setNickname(vo.getNickname());
        user.setGmtModified(new Date());
        user.setStatus(vo.getStatus());

        user = repository.save(user);
        if (user.getId() == null || user.getId() <= 0) {
            return ResultInfo.fail("修改用户失败");
        }

        // 同步UserInfo mobile 字段
        ResultInfo result = userInfoService.updateMobile(user);
        if (!result.isSuccess()) {
            throw new BizException(result);
        }

        // 添加事件
        String remark = String.format("修改用户信息[%s:%s]", user.getId(), user.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.USER_MNG, remark);

        return ResultInfo.ok().message("修改用户成功");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modifyStatus(Long sysUserId, Long userId, Integer status) {
        Optional<User> optional = repository.findById(userId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("用户不存在");
        }
        User user = optional.get();

        int rows = repository.updateStatusById(userId, status);
        if (rows <= 0) {
            return ResultInfo.fail("修改用户状态失败");
        }

        // 添加事件
        String remark = String.format("修改用户状态[%s:%s]", user.getId(), user.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.USER_MNG, remark);

        return ResultInfo.ok().message("修改用户状态成功");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo loginUnlock(Long sysUserId, Long userId) {
        Optional<User> optional = repository.findById(userId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("用户不存在");
        }
        User user = optional.get();

        int rows = repository.updateLoginErrorInfoById(userId, DictConsts.USER_STATUS_DISABLE);
        if (rows <= 0) {
            return ResultInfo.fail("解锁用户登录失败");
        }

        // 添加事件
        String remark = String.format("解锁用户登录[%s:%s]", user.getId(), user.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.USER_MNG, remark);

        return ResultInfo.ok().message("解锁用户登录成功");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo resetPassword(Long sysUserId, Long userId) {
        Optional<User> optional = repository.findById(userId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("用户不存在");
        }
        User user = optional.get();

        Object obj = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.DEFAULT_PWD);
        if (obj == null) {
            return ResultInfo.fail("获取系统初始密码失败");
        }
        String salt = StrUtils.randomStr(20);
        String password = ShiroUtils.md5WithSalt(salt, obj + "");
        int rows = repository.updatePasswordById(userId, salt, password);
        if (rows <= 0) {
            return ResultInfo.fail("重置管理员登录密码失败");
        }

        // 添加事件
        String remark = String.format("重置用户密码[%s:%s]", user.getId(), user.getUsername());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.USER_MNG, remark);

        return ResultInfo.ok().message("重置用户密码成功");
    }

    public User findById(Long userId) {
        Optional<User> optional = repository.findById(userId);
        return optional.orElse(null);
    }
}
