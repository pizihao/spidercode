package cn.spider.colaDerive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "cn.spider.colaDerive", "com.alibaba.cola" })
@MapperScan("cn.spider.colaDerive.gatewayimpl.database")
public class ColaDeriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColaDeriveApplication.class, args);
    }

}
