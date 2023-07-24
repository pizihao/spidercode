package com.example.druidtest.model.entity;

/**
 * 请求实体，
 */
public interface Request<T> extends Key, Header {

    /**
     * 请求发送的时间戳
     *
     * @return long
     */
    long timestamp();

    /**
     * 获取请求数据
     *
     * @return T
     */
    T data();

}
