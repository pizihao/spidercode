package com.binder.element;

import com.binder.ElementUnit;
import com.binder.source.SourceName;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Supported type elements
 *
 * @author Create by liuwenhao on 2022/10/12 16:05
 */
public interface Element {

    boolean isSupport(Type type);

    /**
     * Parsing Configuration Information
     *
     * @param elementUnit prefix and name
     * @param elements    all element
     * @param <T>         The type obtained after parsing
     * @return T The parsed type
     */
    @SuppressWarnings("unchecked")
    default <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (elements.isSupport(this)) {
            return null;
        }
        List<SourceName> e = elementUnit.getSourceNames();
        String prefix = elementUnit.getPrefix();
        String name = elementUnit.getName();
        SourceName sourceName = e.stream()
                .filter(s -> s.getFullName().equals(prefix))
                .filter(s -> s.getSimpleName().equals(name))
                .findFirst()
                .orElse(new SourceName());
        Object obj = sourceName.getObj();
        return (obj == null ? null : (T) obj);
    }

}
