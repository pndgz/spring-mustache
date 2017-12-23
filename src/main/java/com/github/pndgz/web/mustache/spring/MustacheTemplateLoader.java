package com.github.pndgz.web.mustache.spring;

import com.github.mustachejava.MustacheResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by gordondu on 15/8/10.
 */
public class MustacheTemplateLoader implements MustacheResolver {

    private static final Logger logger = LoggerFactory.getLogger(MustacheTemplateLoader.class);

    private ResourceLoader resourceLoader;
    private String encoding = "utf-8";

    @Override
    public Reader getReader(String resourceName) {
        Resource resource = resourceLoader.getResource(resourceName);
        if (resource.exists()) {
            try {
                logger.debug("MustacheTemplateLoader load resource {} with {}", resourceName, encoding);
                return new InputStreamReader(resource.getInputStream(), encoding);
            } catch (Exception ex) {
                logger.error(ex.getLocalizedMessage(), ex);
            }
        } else {
            logger.error("Cannot found resource template {}", resourceName);
        }
        return null;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
