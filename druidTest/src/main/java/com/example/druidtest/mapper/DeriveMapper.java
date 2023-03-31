package com.example.druidtest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.druidtest.model.DeriveDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/25 13:41
 */
@Mapper
public interface DeriveMapper extends BaseMapper<DeriveDO> {
    void add(@Param("list") List<DeriveDO> list);
}
