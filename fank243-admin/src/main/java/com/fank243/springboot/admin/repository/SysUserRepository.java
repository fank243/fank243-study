package com.fank243.springboot.admin.repository;

import com.fank243.springboot.core.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 管理员持久层
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:08:02
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 根据ID查找
     * 
     * @param sysUserId 管理员ID
     * @return 操作结果
     */
    SysUser findByIdAndIsDeletedFalse(Long sysUserId);

    /**
     * 根据用户名查找
     * 
     * @param username 用户名
     * @return Admin
     */
    SysUser findByUsernameAndIsDeletedFalse(String username);

    /**
     * 根据手机号码查找
     * 
     * @param mobile 用户名
     * @return Admin
     */
    SysUser findByMobileAndIsDeletedFalse(String mobile);

    /**
     * 根据用户名查找
     *
     * @param username 用户名
     * @return Admin
     */
    SysUser findByUsername(String username);

    /**
     * 根据手机号码查找
     *
     * @param mobile 用户名
     * @return SysUser
     */
    SysUser findByMobile(String mobile);

    /**
     * 根据用户名和ID查找
     *
     * @param username 用户名
     * @param sysUserId 管理员ID
     * @return Admin
     */
    int countByUsernameAndIdNot(String username, Long sysUserId);

    /**
     * 根据手机号码和ID查找
     *
     * @param mobile 用户名
     * @param sysUserId 管理员ID
     * @return Admin
     */
    int countByMobileAndIdNot(String mobile, Long sysUserId);

    /**
     * 删除管理员(逻辑删除)
     * 
     * @param sysUserId 管理员ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update SysUser set isDeleted = true ,deletedTime = now() where id =:sysUserId")
    int updateIsDeletedById(Long sysUserId);

    /**
     * 修改登录错误次数
     *
     * @param sysUserId 管理员ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("UPDATE SysUser set loginErrCount = loginErrCount + 1 where id = ?1")
    void updateLoginErrCount(Long sysUserId);

    /**
     * 修改状态及登录锁定时间
     *
     * @param userId 管理员ID
     * @param status 状态
     * @param loginLockTime 登录锁定时间
     * 
     */
    @Transactional
    @Modifying
    @Query("UPDATE SysUser set status = ?2, loginLockTime = ?3 where id = ?1")
    int updateStatusAndLoginLockTime(Long userId, Integer status, LocalDateTime loginLockTime);

    SysUser findByUsernameAndIdNot(String username, long sysUserId);

    SysUser findByMobileAndIdNot(String username, long sysUserId);

    SysUser findByWxNumber(String wxNumber);

    SysUser findByWxNumberAndIdNot(String wxNumber, Long id);

    @Transactional
    @Modifying
    @Query(value = "update SysUser set status =:status where id =:sysUserId")
    int updateStatusById(Long sysUserId, int status);

    @Transactional
    @Modifying
    @Query(value = "update SysUser set status =:status,loginErrCount=0,loginLockTime = null where id =:sysUserId")
    int updateLoginErrorInfoById(Long sysUserId, int status);

    @Transactional
    @Modifying
    @Query(value = "update SysUser set salt =:salt,password =:password where id =:sysUserId")
    int updatePasswordById(Long sysUserId, String salt, String password);
}
