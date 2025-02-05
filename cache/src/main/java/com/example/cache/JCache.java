package com.example.cache;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface JCache<T> extends JCacheConfig<T> {

    <K, V> V get(K k);

    <K, V> V getIfAbsent(K k, Supplier<V> supplier);

    <K, V> void set(K k, V s);

    <K, V> void set(K k, Supplier<V> supplier);

    <K, V> void set(Supplier<K> keySupplier, Supplier<V> valueSupplier);

    <K, V> void operation(K k, Consumer<V> consumer);

}
