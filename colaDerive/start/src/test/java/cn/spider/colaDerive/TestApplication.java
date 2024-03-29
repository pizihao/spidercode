package cn.spider.colaDerive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {TestApplication.class})})
@SpringBootApplication(scanBasePackages = {"com.alibaba.craftsman"})
public class TestApplication {

    public static void main(String[] args) {
        //这里填的是TestApplication
        ApplicationContext context = SpringApplication.run(TestApplication.class, args);
    }

}
