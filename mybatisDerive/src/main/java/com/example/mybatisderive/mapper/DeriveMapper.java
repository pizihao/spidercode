package com.example.mybatisderive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisderive.model.DO.DeriveDO;
import com.example.mybatisderive.model.DTO.QueryDTO;

import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/25 13:41
 */
public interface DeriveMapper extends BaseMapper<DeriveDO> {
    List<DeriveDO> selectByCondition(QueryDTO dto);
}
