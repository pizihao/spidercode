package com.binder.element;

import com.binder.ElementUnit;
import com.binder.source.SourceName;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class EnumElement implements Element {
    @Override
    public boolean isSupport(Type type) {
        if (type instanceof ParameterizedType) {
            return false;
        }
        return ((Class<?>) type).isEnum();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (elements.isSupport(this)) {
            return null;
        }
        List<SourceName> e = elementUnit.getSourceNames();
        String prefix = elementUnit.getPrefix();
        String name = elementUnit.getName();
        Object obj = e.stream()
                .filter(s -> s.getFullName().equals(prefix))
                .filter(s -> s.getSimpleName().equals(name))
                .findFirst()
                .orElse(new SourceName())
                .getObj();
        Class<?> cls = (Class<?>) elementUnit.getType();
        Object[] constants = cls.getEnumConstants();

        for (Object constant : constants) {
            if (constant.toString().equals(obj)) {
                return (T) constant;
            }
        }
        return null;
    }
}
