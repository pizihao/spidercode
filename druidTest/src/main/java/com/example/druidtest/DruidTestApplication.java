package com.example.druidtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.example.druidtest.mapper")
public class DruidTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DruidTestApplication.class, args);
    }

}
