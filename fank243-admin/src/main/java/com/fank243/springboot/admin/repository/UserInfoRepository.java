package com.fank243.springboot.admin.repository;

import com.fank243.springboot.core.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户详细信息
 * 
 * @author FanWeiJie
 * @date 2020-03-25 23:39:50
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Transactional
    @Modifying
    @Query("update UserInfo set mobile =:mobile,gmtModified = now() where userId =:userId")
    int updateMobileByUserId(@Param("mobile") String mobile, @Param("userId") Long userId);
}
