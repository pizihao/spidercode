package com.binder.element;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Create by liuwenhao on 2022/10/12 16:21
 */
public class SimpleElement implements Element {

    final List<Class<?>> simple = new LinkedList<>();

    public SimpleElement() {
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
    public boolean isSupport(Type type) {
        if (Objects.isNull(type) || type instanceof ParameterizedType) {
            return false;
        }
        Class<?> cls = (Class<?>) type;
        return isSimple(cls);
    }

    private boolean isSimple(Class<?> cls) {
        for (Class<?> sim : simple) {
            if (sim.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }
}
