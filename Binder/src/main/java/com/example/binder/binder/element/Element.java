package com.example.binder.binder.element;

import com.example.binder.binder.source.Source;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持的类型元素
 *
 * @author Create by liuwenhao on 2022/10/12 16:05
 */
public interface Element {

    /**
     * 元素解析的枚举
     *
     * @return ElementEnum
     */
    default ElementEnum supportType() {
        return null;
    }

    /**
     * 支持的元素类型
     *
     * @return List<Class < ?>>
     */
    default List<Class<?>> supportClasses() {
        return new ArrayList<>();
    }

    /**
     * 解析配置信息
     *
     * @param e   单个的配置项
     * @param <T> 解析后获得的类型
     * @return T 解析后的类型
     */
    default <T> T parser(Source e) {
        return null;
    }

    enum ElementEnum {

        STRING,

        ARRAY,

        PRIMITIVE,

        COLLECTION,

        OBJECT,

        MAP;

    }
}
