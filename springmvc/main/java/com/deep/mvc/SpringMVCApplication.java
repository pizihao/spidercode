package com.deep.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringMVCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMVCApplication.class, args);
    }

}
