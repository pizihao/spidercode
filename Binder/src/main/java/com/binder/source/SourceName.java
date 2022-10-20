package com.binder.source;


import com.binder.element.Element;
import lombok.*;

/**
 * 用于标识一个配置项的name，用于区别不同{@link Element}的配置信息
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SourceName {

    /**
     * 配置项整体的前缀
     */
    String prefix;

    /**
     * 单个元素的标识
     */
    String ElementName;

    /**
     * 元素的序号，针对数组和集合提供的索引序号，如果是-1，则不是数组和集合类型
     */
    Integer index;

    /**
     * 可以唯一确定的值
     */
    Object obj;

    public SourceName(String prefix, String elementName, Integer index, Object obj) {
        this.prefix = prefix;
        ElementName = elementName;
        this.index = index;
        this.obj = obj;
    }
}
