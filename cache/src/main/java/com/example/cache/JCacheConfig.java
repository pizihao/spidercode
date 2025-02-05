package com.example.cache;

import java.util.Collections;
import java.util.List;

public interface JCacheConfig<T> {

    /**
     * 淘汰策略
     */
    default ReplacementPolicy obsolescence() {
        return new ReplacementPolicy();
    }

    /**
     * 默认过期时间
     */
    default long defaultTimeout() {
        return 0;
    }

    /**
     * 最大值
     */
    default long maximumSize() {
        return Integer.MAX_VALUE;
    }

    /**
     * 初始化值
     */
    default int initialCapacity() {
        return 0;
    }

    /**
     * 计算过期的起始点<br>
     * <ol>
     *     <li>从写入开始计算，读取不重置</li>
     *     <li>从写入开始计算，读取后重置</li>
     * </ol>
     */
    default int expireType() {
        return 0;
    }

    /**
     * 添加key的监听器
     */
    default List<CacheListener<T>> addListener() {
        return Collections.emptyList();
    }

    /**
     * 修改key的监听器
     */
    default List<CacheListener<T>> mergeListener() {
        return Collections.emptyList();
    }

    /**
     * 驱逐key的监听器
     */
    default List<CacheListener<T>> evictionListener() {
        return Collections.emptyList();
    }
}
