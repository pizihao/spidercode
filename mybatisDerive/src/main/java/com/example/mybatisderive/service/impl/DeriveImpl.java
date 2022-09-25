package com.example.mybatisderive.service.impl;

import com.example.mybatisderive.mapper.DeriveMapper;
import com.example.mybatisderive.model.DO.DeriveDO;
import com.example.mybatisderive.model.DTO.QueryDTO;
import com.example.mybatisderive.service.DeriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/25 13:40
 */
@Service
@RequiredArgsConstructor
public class DeriveImpl implements DeriveService {

    final DeriveMapper deriveMapper;

    @Override
    public Integer save(DeriveDO deriveDO) {
        return deriveMapper.insert(deriveDO);
    }

    @Override
    public List<DeriveDO> select(QueryDTO dto) {
        return deriveMapper.selectByCondition(dto);
    }
}
