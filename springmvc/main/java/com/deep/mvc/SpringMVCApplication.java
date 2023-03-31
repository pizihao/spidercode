package com.deep.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.deep.mvc.mapper")
public class SpringMVCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMVCApplication.class, args);
    }

}
