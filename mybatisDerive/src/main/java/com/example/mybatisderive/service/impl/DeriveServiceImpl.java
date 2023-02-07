package com.example.mybatisderive.service.impl;

import com.example.mybatisderive.mapper.DeriveMapper;
import com.example.mybatisderive.model.DO.DeriveDO;
import com.example.mybatisderive.model.DTO.QueryDTO;
import com.example.mybatisderive.service.DeriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/25 13:40
 */
@Service
@RequiredArgsConstructor
public class DeriveServiceImpl implements DeriveService {

    final DeriveMapper deriveMapper;
    final TransactionTemplate transactionTemplate;

    @Override
    public Integer save(DeriveDO deriveDO) {

        transactionTemplate.executeWithoutResult(t -> {
            deriveMapper.insert(deriveDO);
            Object savepoint = t.createSavepoint();
            deriveMapper.insert(deriveDO);
            t.rollbackToSavepoint(savepoint);

            Object savepoint1 = t.createSavepoint();
            deriveMapper.insert(deriveDO);
            t.releaseSavepoint(savepoint1);
        });

        return deriveMapper.insert(deriveDO);
    }

    @Override
    public List<DeriveDO> select(QueryDTO dto) {
        return deriveMapper.selectByCondition(dto);
    }

    @Override
    @Transactional
    public void insert() {
        DeriveDO deriveDO = new DeriveDO();
        deriveDO.setName("第一个");
        deriveDO.setDate(LocalDateTime.now());
        deriveDO.setAddress("第一个地址");
        deriveMapper.insert(deriveDO);
    }

    public void insert2() {
        transactionTemplate.executeWithoutResult(t -> {

            DeriveDO deriveDO = new DeriveDO();
            deriveDO.setName("lsj");
            deriveDO.setDate(LocalDateTime.now());
            deriveDO.setAddress("sssd");
            deriveMapper.insert(deriveDO);
            System.out.println("提交内部事务");
        });
    }

    @Override
    public void insertDerive() {
        transactionTemplate.executeWithoutResult(t -> {
            DeriveDO deriveDO = new DeriveDO("name", "address", LocalDateTime.now());
            deriveMapper.insert(deriveDO);
            insert2();
            System.out.println("提交外部事务");
        });
    }

}
