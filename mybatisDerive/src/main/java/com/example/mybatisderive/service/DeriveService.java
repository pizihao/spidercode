package com.example.mybatisderive.service;

import com.example.mybatisderive.model.DO.DeriveDO;
import com.example.mybatisderive.model.DTO.QueryDTO;

import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/25 13:40
 */
public interface DeriveService {
    Integer save(DeriveDO deriveDO);

    List<DeriveDO> select(QueryDTO dto);

    void insert();

    void insertDerive();

    void rollbackTest();
}
