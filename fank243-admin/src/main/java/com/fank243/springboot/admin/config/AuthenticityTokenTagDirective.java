package com.fank243.springboot.admin.config;

import com.fank243.springboot.admin.utils.WebUtils;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.IConsts;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * freemarker 自定义标签
 * 
 * <@authenticityToken/>
 * 
 * @author FanWeiJie
 * @date 2020-07-02 09:51:33
 */
@Component
public class AuthenticityTokenTagDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
        throws TemplateException, IOException {

        String uuid = StrUtils.getUUID();

        HttpSession session = WebUtils.getRequest().getSession();
        session.setAttribute(IConsts.AUTHENTICITY_TOKEN, uuid);

        body = out -> out.append("<input type='hidden' name='" + IConsts.AUTHENTICITY_TOKEN + "' value='").append(uuid)
            .append("'/>");
        body.render(env.getOut());
    }
}
