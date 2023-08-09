package com.example.druidtest.util;

import java.io.IOException;

public interface IBuffer<T> {

    /**
     * 当前任务的状态
     *
     * @return 状态
     */
    int state();

    /**
     * 写入记录
     *
     * @param r 记录
     * @return 写入的记录占用总字节数
     */
    boolean write(T r);

    /**
     * 关闭这个任务
     */
    void close() throws IOException;

    /**
     * 获取当前占用的字节总数
     *
     * @return 字节数
     */
    long size();

    /**
     * 写入数据的时间戳，每次写入数据都需要进行更新
     *
     * @return 时间戳
     */
    long timestamp();

    /**
     * 活跃
     */
    int ACTIVE = 1;

    /**
     * 阻塞
     */
    int BLOCK = 2;

    /**
     * 可关闭状态，表示已弃用
     */
    int CLOSEABLE = 3;

}
