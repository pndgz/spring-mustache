package com.github.pndgz.web.mustache;

import com.github.pndgz.web.mustache.spring.MustacheFactory;
import com.github.pndgz.web.mustache.spring.MustacheTemplateLoader;
import com.github.pndgz.web.mustache.spring.DateFormatReflectionObjectHandler;
import com.github.mustachejava.Mustache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by gordondu on 15/8/10.
 */
public class MustacheHelper implements ResourceLoaderAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MustacheFactory mustacheFactory;
    private MustacheTemplateLoader mustacheTemplateLoader;

    private String prefix = "/templates/";
    private String suffix = ".mustache";
    private String charset = "utf-8";

    public MustacheHelper() {
        mustacheTemplateLoader = new MustacheTemplateLoader();
        mustacheFactory = new MustacheFactory(mustacheTemplateLoader);
        mustacheFactory.setObjectHandler(new DateFormatReflectionObjectHandler());
    }

    public String buildTemplate(String templateName, Map<String, Object> model){
        StringWriter stringWriter = new StringWriter();
        String url = prefix + templateName + suffix;
        logger.debug("MustacheHelper build template {}", url);
        Mustache template = mustacheFactory.compile(mustacheTemplateLoader.getReader(url), url);
        template.execute(stringWriter, model);
        return stringWriter.toString();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setCharset(String charset) {
        this.charset = charset;
        mustacheTemplateLoader.setEncoding(charset);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        mustacheTemplateLoader.setResourceLoader(resourceLoader);
    }
}
