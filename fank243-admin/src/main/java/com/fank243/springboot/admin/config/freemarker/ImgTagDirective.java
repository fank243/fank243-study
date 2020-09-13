package com.fank243.springboot.admin.config.freemarker;

import com.fank243.springboot.common.redis.RedisKey;
import com.fank243.springboot.common.utils.CacheUtils;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.consts.SysKey;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * freemarker 自定义标签
 *
 * <@img style='cursor: pointer' id="" src="" alt="" width="" height=""/>
 *
 * @author FanWeiJie
 * @date 2020-07-02 09:51:33
 */
@Component
public class ImgTagDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
        throws TemplateException, IOException {

        String id = params.get("id") + "";
        String src = params.get("src") + "";
        String alt = params.get("alt") + "";
        // 默认图片类型，0：默认图片，1：默认头像图片
        int imgType = StrUtils.strToInt(params.get("imgType") + "", 0);
        // 图片宽度
        String width = StrUtils.strToStr(params.get("width") + "", "100%");
        // 图片高度
        String height = StrUtils.strToStr(params.get("height") + "", "20%");

        src = src.replaceAll("defined", "").replaceAll("null", "");
        alt = alt.replaceAll("defined", "").replaceAll("null", "");

        // 默认图片
        String defaultImg = IConsts.DEFAULT_IMG;

        // 默认图片类别，用于图片加载异常时展示默认图片，1：头像图片、其他：默认图片
        if (imgType == 2) {
            defaultImg = IConsts.DEFAULT_PHOTO;
        }

        String ossDomain = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.ALIYUN_OSS_DOMAIN) + "";
        src = ossDomain + src;

        String finalSrc = src, finalAlt = alt, finalDefaultImg = defaultImg;

        body = out -> out.append("<img style='cursor: pointer'")//
            .append("id='").append(id).append("'")//
            .append("src='").append(finalSrc).append("'")//
            .append("alt='").append(finalAlt).append("'")//
            .append("width='").append(String.valueOf(width)).append("'")//
            .append("height='").append(String.valueOf(height)).append("'")//
            .append("onerror=\"this.src='").append(finalDefaultImg).append("'\"")//
            .append("onmouseenter=\"showTips(this,'点击预览图片')\"")//
            .append("onclick='previewImg2(this)'")//
            .append("/>");
        body.render(env.getOut());
    }
}
