package com.fank243.springboot.admin.config;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

/**
 * Freemarker Configuration
 * 
 * 加载classpath:freemarker-static.propertie文件，读取静态类全限定名
 * 
 * 主要用于页面直接调用上述类中的静态成员变量、方法、常量、枚举等
 * 
 * @author FanWeiJiei
 * @date 2018/8/17 17:55
 **/
@Configuration
public class FreemarkerConfig extends FreeMarkerViewResolver {

    @Autowired
    protected FreeMarkerViewResolver freeMarkerViewResolver;

    @PostConstruct
    public void setStaticModels() {
        FreemarkerStaticModels staticModels = FreemarkerStaticModels.getInstance();

        // 加载静态类
        Properties properties = new Properties();
        try {
            properties.load(FreemarkerConfig.class.getResourceAsStream("/staticClass.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        staticModels.setStaticModels(properties);

        freeMarkerViewResolver.setAttributesMap(staticModels);
    }

    static class FreemarkerStaticModels extends HashMap<String, Object> {
        private static final long serialVersionUID = 2612797213260493786L;

        private static FreemarkerStaticModels FREEMARKER_STATIC_MODELS;

        private Properties staticModels;

        private FreemarkerStaticModels() {}

        static FreemarkerStaticModels getInstance() {
            if (FREEMARKER_STATIC_MODELS == null) {
                FREEMARKER_STATIC_MODELS = new FreemarkerStaticModels();
            }
            return FREEMARKER_STATIC_MODELS;
        }

        void setStaticModels(Properties properties) {
            if (this.staticModels == null && properties != null) {
                this.staticModels = properties;
                Set<String> keys = this.staticModels.stringPropertyNames();
                for (String key : keys) {
                    FREEMARKER_STATIC_MODELS.put(key, useStaticPackage(this.staticModels.getProperty(key)));
                }
            }
        }

        static TemplateHashModel useStaticPackage(String packageName) {
            try {
                BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
                TemplateHashModel staticModels = wrapper.getStaticModels();
                return (TemplateHashModel)staticModels.get(packageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

//    @Autowired
//    private freemarker.template.Configuration configuration;
//    @Autowired
//    private ApplicationContext applicationContext;

//    @PostConstruct
//    public void setSharedVariable() {
//        // configuration.setSharedVariable("author_contents",
//        // applicationContext.getBean(AuthorContentsDirective.class));
//        // configuration.setSharedVariable("channel", applicationContext.getBean(ChannelDirective.class));
//        // configuration.setSharedVariable("contents", applicationContext.getBean(ContentsDirective.class));
////        configuration.setSharedVariable("num", applicationContext.getBean(NumberDirective.class));
////        configuration.setSharedVariable("resource", applicationContext.getBean(ResourceDirective.class));
////        configuration.setSharedVariable("menus", applicationContext.getBean(MenusDirective.class));
////        // configuration.setSharedVariable("banner", applicationContext.getBean(BannerDirective.class));
////
////        configuration.setSharedVariable("timeAgo", new TimeAgoMethod());
////        configuration.setSharedVariable("shiro", new ShiroTags());
//    }
}
