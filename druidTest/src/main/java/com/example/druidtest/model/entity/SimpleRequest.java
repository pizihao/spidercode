package com.example.druidtest.model.entity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleRequest<T> implements Request<T> {

    /**
     * 关键字
     */
    String key;

    /**
     * header
     */
    Map<String, Object> header;

    /**
     * data
     */
    T data;

    /**
     * request的发送时间点
     */
    long timestamp;

    public SimpleRequest(String key, T data) {
        this.key = key;
        this.data = data;
        this.header = new LinkedHashMap<>();
        this.timestamp = new Date().getTime();
    }

    @Override
    public Map<String, Object> getHeader() {
        return header;
    }

    @Override
    public Object getHeader(String s) {
        return header.get(s);
    }

    @Override
    public void appendHeader(String s, Object o) {
        header.put(s, o);
    }

    @Override
    public void setHeader(Map<String, Object> map) {
        this.header = map;
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
    public long timestamp() {
        return timestamp;
    }

    @Override
    public T data() {
        return data;
    }

    @Override
    public String toString() {
        return "SimpleRequest{" +
                "key='" + key + '\'' +
                ", header=" + header +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
