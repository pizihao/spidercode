package com.example.druidtest.model.entity;

/**
 * 请求的响应
 *
 * @param <T> 返回类型
 */
public interface Response<T> extends Key {

    /**
     * 响应数据
     *
     * @return T
     */
    T data();

    /**
     * 响应中可能存在的信息
     *
     * @return message
     */
    String msg();

    /**
     * 请求是否成功
     *
     * @return 状态
     */
    boolean isSuccess();

}
