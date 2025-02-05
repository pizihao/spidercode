package com.example.cache;

import java.util.Queue;

public class CacheValue {

    /**
     * 缓存的数据
     */
    Object source;

    /**
     * 写入到缓存中的时间戳
     */
    long writeTimestamp;

    /**
     * 上次访问时的时间戳
     */
    long visitTimestamp;

    /**
     * 总的访问次数
     */
    int numberOfVisits;

    /**
     * 以队列的形式记录最近的几次访问时间戳，饱和策略为丢弃队列头部的数据
     */
    Queue<Long> accessTimestamp;

    /**
     * 大小，B
     */
    long length;

    public CacheValue(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
