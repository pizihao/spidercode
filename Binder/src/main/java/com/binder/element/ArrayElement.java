package com.binder.element;

import com.binder.ElementUnit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Create by liuwenhao on 2022/10/12 16:06
 */
public class ArrayElement implements Element {
    @Override
    public boolean isSupport(Type type) {
        if (Objects.isNull(type) || type instanceof ParameterizedType) {
            return false;
        }
        return ((Class<?>) type).isArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (elements.isSupport(this)) {
            return null;
        }
        Collection<Object> parser = elements.getResult(elementUnit);
        return (T) parser.toArray();
    }
}
