package com.abc.common.exception;

import com.abc.controller.userinfo.UserExpController;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by stuy on 2017/9/6.
 */
public class AbcFreemarkerExceptionHandler implements TemplateExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(AbcFreemarkerExceptionHandler.class);

    @Override
    public void handleTemplateException(TemplateException e, Environment environment, Writer writer) throws TemplateException {
        logger.debug("Freemaker异常"+e);
        try {
            logger.info("错误内容:"+e.getMessage());
            writer.write("<script>window.parent.location.href='"+environment.getMainNamespace().get("ctx")+"/500.html'</script>");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
