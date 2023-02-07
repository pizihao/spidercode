package com.example.mybatisderive.extend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TransactionManagerConfig {

    @Bean("devTransactionTemplate")
    public DevTransactionTemplate devTransactionTemplate(DataSource dataSource){
        return new DevTransactionTemplate(new DevDataSourceTransactionManager(dataSource));
    }

}
