package com.binder.element;

import cn.hutool.core.util.ReflectUtil;
import com.binder.ElementUnit;
import com.binder.source.SourceName;
import com.binder.util.BeanUtil;
import com.binder.util.StringUtil;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectElement implements Element {

    @Override
    public boolean isSupport(Type type) {
        return !(type instanceof ParameterizedType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (!elements.isSupport(this)) {
            return null;
        }
        String prefix = elementUnit.getPrefix();
        List<SourceName> e = elementUnit.getSourceNames();
        Class<T> cls = (Class<T>) elementUnit.getType();
        List<Field> fields = getFields(cls);
        try {
            T instance = cls.newInstance();
            fields.forEach(f -> {
                String fieldName = f.getName();
                String nextPrefix = prefix + "." + StringUtil.toSymbolCase(fieldName, '-');
                Object parser = elements.getResult(new ElementUnit(nextPrefix, fieldName, e, f.getGenericType()));
                Method setter = BeanUtil.setter(cls, fieldName);
                if (parser == null) {
                    Method getter = BeanUtil.getter(cls, fieldName);
                    parser = ReflectUtil.invoke(instance, getter);
                }
                ReflectUtil.invoke(instance, setter, parser);
            });
            return instance;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }


    private List<Field> getFields(Class<?> beanClass) {
        List<Field> allFields = new LinkedList<>();
        Class<?> searchType = beanClass;
        while (searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
            searchType = searchType.getSuperclass();
        }
        return allFields.stream()
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                .collect(Collectors.toList());
    }
}
