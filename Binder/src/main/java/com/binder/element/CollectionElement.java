package com.binder.element;

import com.binder.ElementUnit;
import com.binder.source.SourceName;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Create by liuwenhao on 2022/10/12 16:12
 */
public class CollectionElement implements Element {

    @Override
    public boolean isSupport(Type type) {
        if (Objects.isNull(type)){
            return false;
        }
        Class<?> cls;
        if (type instanceof ParameterizedType) {
            cls = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            cls = (Class<?>) type;
        }
        return Collection.class.isAssignableFrom(cls);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (elements.isSupport(this)) {
            return null;
        }
        Type type = elementUnit.getType();
        boolean b = type instanceof ParameterizedType;
        if (!b) {
            // is not ParameterizedType, It's impossible to parse
            return null;
        }
        String prefix = elementUnit.getPrefix();
        String name = elementUnit.getName();
        List<SourceName> e = elementUnit.getSourceNames();
        // group for index
        Map<Integer, List<SourceName>> map = e.stream()
                .filter(s -> s.getSimpleName().equals(name))
                .collect(Collectors.groupingBy(SourceName::getIndex));
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        List<Object> list = new LinkedList<>();
        if (typeArguments.length > 0) {
            Type typeArgument = typeArguments[0];
            Class<?> cls = (Class<?>) typeArgument;
            map.forEach((i, s) -> list.add(elements.getResult(new ElementUnit(prefix, name, s, cls))));

        }
        return (T) list;
    }
}
