package com.fank243.springboot.admin.repository.notice;

import com.fank243.springboot.core.entity.EmailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 电子邮件发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:12:49
 */
@Repository
public interface EmailRecordRepository extends JpaRepository<EmailRecord, Long> {
    @Query(
            value = "select count(*) from tb_record_email where email =:email and date_format(gmt_created,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')",
            nativeQuery = true)
    int countByEmailAndDate(String email);
}
