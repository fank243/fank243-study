package com.fank243.springboot.admin.repository.notice;

import com.fank243.springboot.core.entity.SmsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 短信发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:12:49
 */
@Repository
public interface SmsRecordRepository extends JpaRepository<SmsRecord, Long> {

    @Query(
        value = "select count(*) from tb_record_sms where mobile =:mobile and date_format(gmt_created,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')",
        nativeQuery = true)
    int countByMobileAndDate(String mobile);
}
