package com.fank243.springboot.admin.repository.notice;

import com.fank243.springboot.core.entity.EmailRecord;
import com.fank243.springboot.core.entity.PushRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 推送消息送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:12:49
 */
@Repository
public interface PushRecordRepository extends JpaRepository<PushRecord, Long> {}
