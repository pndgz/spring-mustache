package com.countstar.web.mustache.spring;

import com.github.mustachejava.reflect.Guard;
import com.github.mustachejava.reflect.ReflectionObjectHandler;
import com.github.mustachejava.util.Wrapper;

import java.lang.reflect.AccessibleObject;
import java.util.List;

/**
 * Created by gordondu on 15/8/10.
 */
public class DateFormatReflectionObjectHandler extends ReflectionObjectHandler {

    protected Wrapper createWrapper(int scopeIndex, Wrapper[] wrappers, List<? extends Guard> guard, AccessibleObject member, Object[] arguments) {
        return new DateFormatReflectionWrapper(scopeIndex, wrappers, guard.toArray(new Guard[guard.size()]), member, arguments, this);
    }


}
