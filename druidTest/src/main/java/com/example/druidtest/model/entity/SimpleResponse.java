package com.example.druidtest.model.entity;

public class SimpleResponse<T> implements Response<T>, Key {

    /**
     * 关键字
     */
    String key;

    /**
     * data
     */
    T data;

    /**
     * message
     */
    String msg;

    /**
     * 是否成功
     */
    boolean isSuccess;

    public SimpleResponse(String key, T data, String msg, boolean isSuccess) {
        this.key = key;
        this.data = data;
        this.msg = msg;
        this.isSuccess = isSuccess;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public void key(String k) {
        this.key = k;
    }

    @Override
    public T data() {
        return data;
    }

    @Override
    public String msg() {
        return msg;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }
}
