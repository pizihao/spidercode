package com.binder.element;

import com.binder.source.SourceName;
import com.binder.tes.ConfigFileTypeEnum;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class EnumElement implements Element{
    @Override
    public ElementEnum supportType() {
        return ElementEnum.ENUM;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(String prefix,String name, List<SourceName> e, Type type) {
        Object obj = e.stream()
                .filter(s -> s.getFullName().equals(prefix))
                .filter(s -> s.getSimpleName().equals(name))
                .findFirst()
                .orElse(new SourceName())
                .getObj();
        Class<?> cls = (Class<?>) type;
        Object[] constants = cls.getEnumConstants();

        for (Object constant : constants) {
            if (constant.toString().equals(obj)){
                return (T) constant;
            }
        }
        return null;
    }
}
