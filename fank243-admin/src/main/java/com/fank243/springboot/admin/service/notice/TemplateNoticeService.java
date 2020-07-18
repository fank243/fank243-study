package com.fank243.springboot.admin.service.notice;

import com.fank243.springboot.admin.repository.notice.TemplateNoticeRepository;
import com.fank243.springboot.admin.service.SysUserEventService;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.TemplateNotice;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fank243.springboot.core.enums.TemplateType;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 短信发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 21:13:49
 */
@Service
public class TemplateNoticeService extends BaseService<TemplateNotice> {
    @Resource
    private TemplateNoticeRepository repository;
    @Resource
    private SysUserEventService sysUserEventService;

    public TemplateNotice findByCodeAndType(String code, int type) {
        return repository.findByCodeAndTypeAndAvailableTrue(code, type);
    }

    public PageBean<TemplateNotice> pageOfTemplate(PageInfo pageInfo, Integer type, String keyword) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("SELECT COUNT(*) FROM tb_template_notice WHERE 1=1");
        querySql.append("SELECT * FROM tb_template_notice WHERE 1=1");

        Map<String, Object> conditionArgs = new HashMap<>(2);
        if (StringUtils.isNotBlank(keyword)) {
            countSql.append(" AND (code like :keyword or name like :keyword)");
            querySql.append(" AND (code like :keyword or name like :keyword)");
            conditionArgs.put("keyword", "%" + keyword + "%");
        }

        TemplateType noticeType = TemplateType.getEnum(type);
        if (noticeType != null) {
            countSql.append(" AND type =:type");
            querySql.append(" AND type =:type");
            conditionArgs.put("type", type);
        }
        querySql.append(" ORDER BY code,type,id desc");

        return pageOfBySql(pageInfo, countSql.toString(), querySql.toString(), conditionArgs);
    }

    /**
     * 根据ID查找，模板状态为{@code 可用}
     *
     * @param templateId 模板ID
     * @return 模板
     */
    public TemplateNotice findAvailableById(Long templateId) {
        return repository.findByIdAndAvailableTrue(templateId);
    }

    /**
     * 根据ID查找
     * 
     * @param templateId 模板ID
     * @return 模板
     */
    public TemplateNotice findById(Long templateId) {
        Optional<TemplateNotice> optional = repository.findById(templateId);
        return optional.orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modify(Long sysUserId, TemplateNotice notice) {
        TemplateNotice templateNotice = findById(notice.getId());
        if (templateNotice == null) {
            return ResultInfo.fail("模板不存在");
        }
        if (StringUtils.isBlank(notice.getName())) {
            return ResultInfo.fail("请填写模板名称");
        }
        if (StringUtils.isBlank(notice.getContent())) {
            return ResultInfo.fail("请填写模板内容");
        }

        templateNotice.setName(notice.getName());
        templateNotice.setTitle(notice.getTitle());
        templateNotice.setContent(notice.getContent());
        templateNotice = repository.save(templateNotice);
        if (templateNotice.getId() == null || templateNotice.getId() <= 0) {
            return ResultInfo.fail("修改模板失败");
        }

        String remark = String.format("修改通知模板[%s:%s:%s]", notice.getId(), notice.getType(), notice.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.TEMPLATE_MNG, remark);

        return ResultInfo.ok().message("修改模板成功");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modifyStatus(Long sysUserId, Long templateId) {
        TemplateNotice templateNotice = findById(templateId);
        if (templateNotice == null) {
            return ResultInfo.fail("模板不存在");
        }

        boolean available = !templateNotice.getAvailable();

        int rows = repository.updateAvailableById(templateId, available);
        if (rows <= 0) {
            return ResultInfo.fail("修改模板状态失败");
        }

        String remark = String.format("修改通知模板状态[%s:%s:%s]", templateNotice.getId(), templateNotice.getType(),
            templateNotice.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.TEMPLATE_MNG, remark);

        return ResultInfo.ok().message("修改模板状态成功");
    }

}
