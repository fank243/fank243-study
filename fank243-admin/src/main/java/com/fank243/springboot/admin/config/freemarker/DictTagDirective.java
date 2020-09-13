package com.fank243.springboot.admin.config.freemarker;

import com.fank243.springboot.admin.config.freemarker.dict.DictModel;
import com.fank243.springboot.admin.config.freemarker.dict.DictFormatFactory;
import com.fank243.springboot.admin.config.freemarker.dict.DictFormatInterface;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * freemarker 自定义标签
 * 
 * <@dict eleType="template" dictType="" fieldName=""/>
 * 
 * @author FanWeiJie
 * @date 2020-07-02 09:51:33
 */
@Slf4j
@Component
public class DictTagDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
        throws TemplateException, IOException {

        DictModel dictModel = new DictModel();
        // 校验参数
        try {
            // 用来将一些 key-value 的值（例如 hashmap）映射到 bean 中的属性
            BeanUtils.populate(dictModel, params);
            if (StringUtils.isEmpty(dictModel.getDictType()) || StringUtils.isEmpty(dictModel.getEleType())) {
                throw new IllegalArgumentException("dictType,eleType不能为空");
            }
        } catch (Exception e) {
            log.error("数据转化异常", e);
        }

        String dictHtml = "";
        // 根据类型创建不同的HTML生成器
        DictFormatInterface dictFormatInterface = DictFormatFactory.createInstance(dictModel.getEleType());
        if (dictFormatInterface != null) {
            dictHtml = dictFormatInterface.buildHtml(dictModel.getDictType(), dictModel.getFieldName());
        }

        // 执行真正指令的执行部分:
        Writer out = env.getOut();
        out.write(dictHtml);
        if (body != null) {
            body.render(env.getOut());
        }
    }

    public static BeansWrapper getBeansWrapper() {
        return new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
    }
}
