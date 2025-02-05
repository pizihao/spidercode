package com.example.cache;

public interface GoodsDao {

    void save(Goods goods);

    void update(Goods goods);

    void delete(Integer id);

    Goods get(Integer id);

}
