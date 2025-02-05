package com.example.cache;

/**
 * 监听器
 */
public interface CacheListener<T> {

    T source();

    <K, V> void onListener(K k, V v, T source);

}
