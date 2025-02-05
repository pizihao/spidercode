package com.example.cache;

import com.github.benmanes.caffeine.cache.Cache;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class GoodsCache implements SCache<GoodsDao> {
    Cache<Object, CacheValue> cache;    GoodsDao goodsDao;
    public GoodsCache(GoodsDao goodsDao, Cache<Object, CacheValue> cache) {
        this.goodsDao = goodsDao;
        this.cache = cache;
    }

    @Override
    public GoodsDao source() {
        return goodsDao;
    }

    @Override
    public <K, V> V get(K k) {
        return (V) cache.getIfPresent(k).getSource();
    }

    @Override
    public <K, V> V getIfAbsent(K k, Function<GoodsDao, V> function) {
        CacheValue value = cache.get(k, o -> new CacheValue(function.apply(goodsDao)));
        return (V) value.getSource();
    }

    @Override
    public <K, V> void set(K k, V v) {
        cache.put(k, new CacheValue(v));
    }

    @Override
    public <K, V> void set(K k, Function<GoodsDao, V> valueFunction) {
        cache.put(k, new CacheValue(valueFunction.apply(goodsDao)));
    }

    @Override
    public <K, V> void set(Function<GoodsDao, K> keySupplier, Function<GoodsDao, V> valueFunction) {
        cache.put(keySupplier.apply(goodsDao), new CacheValue(valueFunction.apply(goodsDao)));
    }

    @Override
    public <K, V> void operation(K k, BiConsumer<GoodsDao, V> consumer) {
        consumer.accept(goodsDao, get(k));
    }
}
