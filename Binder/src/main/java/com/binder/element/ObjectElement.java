package com.binder.element;

import cn.hutool.core.util.ReflectUtil;
import com.binder.source.SourceName;
import com.binder.util.BeanUtil;
import com.binder.util.StringUtil;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectElement implements Element {


    List<Class<?>> simple = new LinkedList<>();

    public ObjectElement() {
        simple.add(Integer.class);
        simple.add(Float.class);
        simple.add(Character.class);
        simple.add(Long.class);
        simple.add(Double.class);
        simple.add(Short.class);
        simple.add(Boolean.class);
        simple.add(Byte.class);
        simple.add(String.class);
    }

    @Override
    public ElementEnum supportType() {
        return ElementEnum.OBJECT;
    }

    @Override
    public boolean isSupport(Type type) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(String prefix, String name, List<SourceName> e, Type type) {
        Class<T> cls;
        if (type instanceof ParameterizedType) {
            cls = (Class<T>) ((ParameterizedType) type).getRawType();
        } else {
            cls = (Class<T>) type;
        }
        // TODO 添加新的接口专门判断类型
        if (isSimple(cls)) {
            return simpleElement.parser(prefix, name, e, type);
        }
        if (cls.isArray()) {
            return arrayElement.parser(prefix, name, e, type);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            return collectionElement.parser(prefix, name, e, type);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return mapElement.parser(prefix, name, e, type);
        }
        if (cls.isEnum()) {
            return enumElement.parser(prefix, name, e, type);
        }
        List<Field> fields = getFields(cls);
        try {
            T instance = cls.newInstance();
            fields.forEach(f -> {
                String fieldName = f.getName();
                String nextPrefix = prefix + "." + StringUtil.toSymbolCase(fieldName, '-');
                Object parser = Element.getResult(nextPrefix, fieldName, e, f.getGenericType());
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

    private boolean isSimple(Class<?> cls) {
        for (Class<?> sim : simple) {
            if (sim.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    private List<Field> getFields(Class<?> beanClass) {
        List<Field> allFields = new LinkedList<>();
        Class<?> searchType = beanClass;
        while (searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
            searchType = searchType.getSuperclass();
        }
        return allFields.stream().filter(f -> !Modifier.isStatic(f.getModifiers())).collect(Collectors.toList());
    }
}
