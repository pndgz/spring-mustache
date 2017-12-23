package com.github.pndgz.web.mustache.spring;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheResolver;

/**
 * Created by gordondu on 09/11/2017.
 */
public class MustacheFactory extends DefaultMustacheFactory {

    private boolean cachePartial = true;

    public MustacheFactory(MustacheResolver mustacheResolver) {
        super(mustacheResolver);
    }

    @Override
    public Mustache compilePartial(String s) {
        if (cachePartial) {
            return super.compilePartial(s);
        } else {
            final Mustache mustache = mc.compile(s);
            mustache.init();
            return mustache;
        }
    }

    /**
     * set no cache partial for develop mode
     * @param cachePartial
     */
    public void setCachePartial(boolean cachePartial) {
        this.cachePartial = cachePartial;
    }
}
