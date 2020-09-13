package com.fank243.springboot.app.repository;

import com.fank243.springboot.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户
 * 
 * @author FanWeiJie
 * @date 2020-03-25 23:39:50
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query(value = "update User set status =:status where id =:userId")
    int updateStatusById(Long userId, Integer status);

    @Transactional
    @Modifying
    @Query(value = "update User set status =:status,loginErrCount=0,loginLockTime = null where id =:userId")
    int updateLoginErrorInfoById(Long userId, Integer status);

    @Transactional
    @Modifying
    @Query(value = "update User set salt =:salt,password =:password where id =:userId")
    int updatePasswordById(Long userId, String salt, String password);

    User findByUsername(String username);

    User findByUsernameAndIdNot(String username, Long id);

    User findByMobile(String mobile);

    User findByMobileAndIdNot(String mobile, Long id);

    User findByIdAndStatus(long userId, int status);
}
