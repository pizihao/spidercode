package com.example.druidtest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.druidtest.model.DeriveDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Create by liuwenhao on 2022/9/25 13:41
 */
@Mapper
@Component
public interface DeriveMapper extends BaseMapper<DeriveDO> {
    void add(@Param("list") List<DeriveDO> list);

    @Async
    default void async(){
        LockSupport.parkNanos(10000000000L);
        System.out.println(555);
    }

}
