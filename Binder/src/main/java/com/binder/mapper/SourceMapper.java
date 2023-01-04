package com.binder.mapper;

/**
 * The key of the mapping
 */
public interface SourceMapper {

    /**
     * The key of conversion
     *
     * @param s key before conversion, it is  prefix + key
     * @return The converted key
     */
    String convert(String s);

    /**
     * The key of reverse conversion
     *
     * @param s key before conversion, it is key
     * @return The converted key
     */
    String reverseConvert(String s);
}
