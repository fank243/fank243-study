package com.fank243.springboot.admin.repository;

import com.fank243.springboot.core.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户事件持久层
 * 
 * @author FanWeiJie
 * @date 2020-03-08 14:09:10
 */
@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {}
