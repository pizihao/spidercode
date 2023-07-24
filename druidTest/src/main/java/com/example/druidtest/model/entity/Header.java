package com.example.druidtest.model.entity;

import java.util.Map;

/**
 * 请求中的Header信息，其组织结构为Map
 */
public interface Header {

    /**
     * 获取整个Header
     *
     * @return map
     */
    Map<String, Object> getHeader();

    /**
     * 通过标识信息获取一个header中的数据
     *
     * @param s header 标识
     * @return obj
     */
    Object getHeader(String s);

    /**
     * 在已有的header对象中追加一个header
     *
     * @param s header 标识
     * @param o header obj
     */
    void appendHeader(String s, Object o);

    /**
     * 设置header，这会完全覆盖之前设置的header
     *
     * @param map map
     */
    void setHeader(Map<String, Object> map);

}
