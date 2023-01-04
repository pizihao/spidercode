package com.binder.source;


import com.binder.element.Elements;

import java.util.List;

/**
 *
 */
public interface Source {

    /**
     * Configure the overall prefix of the source
     *
     * @return prefix
     */
    String getPrefix();

    /**
     * The result after parsing<br>
     * If it is a primitive data type, it is processed by its wrapper type<br>
     * cls Must be able to be instantiated without arguments
     *
     * @param cls The corresponding class after parsing
     * @return obj
     * @
     */
    default <T> T getResult(Class<T> cls) {
        Elements elements = new Elements();
        return getResult(cls, elements);
    }

    <T> T getResult(Class<T> cls, Elements elements);

    /**
     * Gets the parsed configuration collection
     *
     * @return Configuration Collection
     */
    List<SourceName> getSourceValue();

}
