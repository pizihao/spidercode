package com.example.druidtest.model.entity;

/**
 * 请求关键字<br>
 * 接口的唯一标识
 */
public interface Key {
    /**
     * 获取请求关键字
     *
     * @return 请求关键字
     */
    String key();

    /**
     * 设置请求关键字
     *
     * @param k 关键字
     */
    void key(String k);
}
