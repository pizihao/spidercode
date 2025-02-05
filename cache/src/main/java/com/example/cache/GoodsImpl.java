package com.example.cache;

public class GoodsImpl {

    SCache<GoodsDao> cache;

    public GoodsImpl(GoodsCache cache) {
        this.cache = cache;
    }

    public void add(Goods goods) {
        // 不走缓存，直接调用源的方法，无返回值
        cache.execute(g -> g.save(goods));
        // 不走缓存，直接调用源的方法，有返回值
        Goods submit = cache.submit(g -> g.get(1));

        // 走缓存处理，把一个值写入到缓存中，并指定key
        cache.set(goods.getId(), g -> g.get(goods.getId()));
        // 走缓存处理，先进行自己的操作再写入缓存
        cache.set(goods.getId(), g -> {
            g.save(goods);
            return goods;
        });
        // 走缓存处理，从缓存中获取数据，如果没有就使用第二个参数获取
        cache.getIfAbsent(goods.getId(), g -> g.get(goods.getId()));
    }
}
