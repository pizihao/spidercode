package com.example.cache;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public interface SCache<T> extends JCacheConfig<T> {

    T source();

    <K, S> S get(K k);

    <K, S> S getIfAbsent(K k, Function<T, S> function);

    <K, S> void set(K k, S s);

    <K, S> void set(K k, Function<T, S> valueFunction);

    <K, S> void set(Function<T, K> keySupplier, Function<T, S> valueFunction);

    <K, S> void operation(K k, BiConsumer<T, S> consumer);

    default void execute(Consumer<T> consumer) {
        consumer.accept(source());
    }

    default <R> R submit(Function<T, R> function) {
        return function.apply(source());
    }

}
