package com.example.mybatisderive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@AllArgsConstructor
public class SuspendServiceImpl {

    final TransactionTemplate transactionTemplate;

    public void suspend(){
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) transactionTemplate.getTransactionManager();
        transactionTemplate.executeWithoutResult(s -> {
        });
    }

}
