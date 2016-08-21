package com.countstar.web.mustache;

import com.countstar.web.mustache.spring.MustacheTemplateLoader;
import com.countstar.web.mustache.spring.MustacheView;
import com.countstar.web.mustache.spring.DateFormatReflectionObjectHandler;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import java.util.Properties;

/**
 * Created by gordondu on 15/8/10.
 */
public class MustacheViewResolver extends AbstractTemplateViewResolver implements ResourceLoaderAware {

    private static final Logger logger = LoggerFactory.getLogger(MustacheViewResolver.class);

    private MustacheTemplateLoader mustacheTemplateLoader;

    private DefaultMustacheFactory mustacheFactory;

    private Properties globalValues;

    private String charset = "utf-8";

    public MustacheViewResolver() {
        setViewClass(MustacheView.class);
        mustacheTemplateLoader = new MustacheTemplateLoader();
        mustacheTemplateLoader.setEncoding(charset);
        mustacheFactory = new DefaultMustacheFactory(mustacheTemplateLoader);
        mustacheFactory.setObjectHandler(new DateFormatReflectionObjectHandler());
    }

    @Override
    protected Class<?> requiredViewClass() {
        return MustacheView.class;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        this.setExposeSessionAttributes(true);
        this.setExposeRequestAttributes(true);
        final MustacheView mustacheView = (MustacheView)super.buildView(viewName);
        String templateUrl = mustacheView.getUrl();
        logger.debug("MustacheViewResolver buildView {}", templateUrl);
        Mustache template = mustacheFactory.compile(mustacheTemplateLoader.getReader(templateUrl), templateUrl);
        mustacheView.setTemplate(template);
        mustacheView.setContentType(getContentType());
        mustacheView.setGlobalValues(globalValues);
        return mustacheView;
    }

    @Override
    protected String getContentType() {
        return "text/html; charset=" + charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
        mustacheTemplateLoader.setEncoding(charset);
    }

    public void setGlobalValues(Properties globalValues) {
        this.globalValues = globalValues;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        mustacheTemplateLoader.setResourceLoader(resourceLoader);
    }
}
