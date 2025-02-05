package com.example.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JCacheImpl implements JCache<CacheValue> {
    Cache<Object, CacheValue> cache;

    public JCacheImpl() {
        this.cache = Caffeine.newBuilder().build();
    }

    @Override
    public <K, V> V get(K k) {
        CacheValue cacheValue = cache.getIfPresent(k);
        return (V) cacheValue.getSource();
    }

    @Override
    public <K, V> V getIfAbsent(K k, Supplier<V> supplier) {
        CacheValue cacheValue = cache.getIfPresent(k);
        if (Objects.isNull(cacheValue)){
            // 首次进入
            cacheValue = new CacheValue(null);
            cache.put(k, cacheValue);
        }

        if (Objects.isNull(cacheValue.getSource())) {
            V v = supplier.get();
            cacheValue.setSource(v);
            cache.put(k, cacheValue);
        }
        return (V) cacheValue.getSource();
    }

    @Override
    public <K, V> void set(K k, V v) {
        cache.put(k, new CacheValue(v));
    }

    @Override
    public <K, V> void set(K k, Supplier<V> supplier) {
        cache.put(k, new CacheValue(supplier.get()));
    }

    @Override
    public <K, V> void set(Supplier<K> keySupplier, Supplier<V> valueSupplier) {
        cache.put(keySupplier.get(), new CacheValue(valueSupplier.get()));

    }

    @Override
    public <K, V> void operation(K k, Consumer<V> consumer) {
        consumer.accept(get(k));
    }

}
