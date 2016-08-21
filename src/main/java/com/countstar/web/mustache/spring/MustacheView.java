package com.countstar.web.mustache.spring;

import com.github.mustachejava.Mustache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * Created by gordondu on 15/8/10.
 */
public class MustacheView extends AbstractTemplateView {

    private static final Logger logger = LoggerFactory.getLogger(MustacheView.class);

    private Mustache template;

    private Properties globalValues;

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        final Writer writer = response.getWriter();
        try {
            if (model != null && globalValues != null) {
                Enumeration enums = globalValues.propertyNames();
                String key, value;
                while (enums.hasMoreElements()) {
                    key = (String)enums.nextElement();
                    value = globalValues.getProperty(key);
                    model.put(key, value);
                    logger.debug("MustacheView set global property {}={}", key, value);
                }
            }
            template.execute(writer, model);
        } finally {
            writer.flush();
        }
    }

    public void setTemplate(Mustache template) {
        this.template = template;
    }

    public void setGlobalValues(Properties globalValues) {
        this.globalValues = globalValues;
    }
}
