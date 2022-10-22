package com.binder.element;

import cn.hutool.core.util.ReflectUtil;
import com.binder.source.SourceName;
import com.binder.util.BeanUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

public class ObjectElement implements Element {
    @Override
    public ElementEnum supportType() {
        return ElementEnum.OBJECT;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(String name, List<SourceName> e, Type type) {
        Class<T> cls = (Class<T>) type;
        if (cls.isPrimitive()) {
            return simpleElement.parser(name, e, cls);
        }
        if (cls.isArray()) {
            return arrayElement.parser(name, e, cls);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            return collectionElement.parser(name, e, cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return mapElement.parser(name, e, cls);
        }
        List<Field> fields = getFields(cls);
        try {
            T instance = cls.newInstance();
            fields.forEach(f -> {
                String fieldName = f.getName();
                Object parser = objectElement.parser(fieldName, e, f.getType());
                Method setter = BeanUtil.setter(cls, fieldName);
                ReflectUtil.invoke(instance, setter, parser);
            });
            return instance;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }


    private List<Field> getFields(Class<?> beanClass) {
        List<Field> allFields = new ArrayList<>();
        Class<?> searchType = beanClass;
        while (searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
            searchType = searchType.getSuperclass();
        }
        return allFields;
    }
}
