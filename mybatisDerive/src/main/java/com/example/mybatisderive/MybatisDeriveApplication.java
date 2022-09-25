package com.example.mybatisderive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mybatisderive.mapper")
public class MybatisDeriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDeriveApplication.class, args);
    }

}
