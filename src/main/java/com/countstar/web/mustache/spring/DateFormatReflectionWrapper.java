package com.countstar.web.mustache.spring;

import com.github.mustachejava.ObjectHandler;
import com.github.mustachejava.reflect.Guard;
import com.github.mustachejava.reflect.ReflectionWrapper;
import com.github.mustachejava.util.GuardException;
import com.github.mustachejava.util.Wrapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.AccessibleObject;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gordondu on 15/8/10.
 */
public class DateFormatReflectionWrapper extends ReflectionWrapper {

    private SimpleDateFormat dateFormat;

    public DateFormatReflectionWrapper(int scopeIndex, Wrapper[] wrappers, Guard[] guard, AccessibleObject method, Object[] arguments, ObjectHandler oh) {
        super(scopeIndex, wrappers, guard, method, arguments, oh);
    }

    @Override
    public Object call(List<Object> scopes) throws GuardException {
        DateTimeFormat format = method.getAnnotation(DateTimeFormat.class);
        Object obj = super.call(scopes);
        if (format != null && obj != null) {
            dateFormat = new SimpleDateFormat(format.pattern());
            return dateFormat.format(obj);
        } else {
            return obj;
        }
    }

}
