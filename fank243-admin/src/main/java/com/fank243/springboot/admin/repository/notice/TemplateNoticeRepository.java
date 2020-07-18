package com.fank243.springboot.admin.repository.notice;

import com.fank243.springboot.core.entity.TemplateNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 通知模板
 * 
 * @author FanWeiJie
 * @date 2020-03-26 22:01:04
 */
@Repository
public interface TemplateNoticeRepository extends JpaRepository<TemplateNotice, Long> {

    TemplateNotice findByCodeAndTypeAndAvailableTrue(String templateCode, int type);

    TemplateNotice findByIdAndAvailableTrue(Long templateId);

    @Modifying
    @Query(value = "update TemplateNotice set available =:available where id =:templateId")
    int updateAvailableById(Long templateId, boolean available);
}
