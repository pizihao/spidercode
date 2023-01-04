package com.binder.element;

import com.binder.ElementUnit;
import com.binder.source.SourceName;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Create by liuwenhao on 2022/10/12 16:12
 */
public class MapElement implements Element {

    @Override
    public boolean isSupport(Type type) {
        if (type instanceof ParameterizedType) {
            Class<?> cls = (Class<?>) ((ParameterizedType) type).getRawType();
            return Map.class.isAssignableFrom(cls);
        } else {
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (elements.isSupport(this)) {
            return null;
        }
        boolean b = elementUnit.getType() instanceof ParameterizedType;
        List<SourceName> e = elementUnit.getSourceNames();
        String prefix = elementUnit.getPrefix();
        if (!b) {
            // is not ParameterizedType, It's impossible to parse
            return null;
        }
        return (T) e.stream()
                .filter(s -> s.getPrefix().equals(prefix))
                .collect(Collectors.toMap(
                        SourceName::getOriginalName,
                        SourceName::getObj,
                        (f, l) -> f
                ));
    }
}
