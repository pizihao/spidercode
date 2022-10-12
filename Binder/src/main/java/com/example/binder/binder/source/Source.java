package com.example.binder.binder.source;

import com.example.binder.binder.element.Element;

/**
 * @author Create by liuwenhao on 2022/10/12 16:31
 */
public interface Source {

    /**
     * 配置源的整体前缀
     *
     * @return prefix
     */
    default String getPrefix() {
        return null;
    }

    /**
     * 声明中除了前缀的部分
     *
     * @return key
     */
    default String getKey() {
        return null;
    }

    /**
     * 解析后的结果<br>
     * 结果是{@link Element.ElementEnum}中的定义<br>
     * 如果是基本数据类型，则会通过其包装类型处理
     *
     * @return obj
     */
    default Object getResult() {
        return null;
    }

}
