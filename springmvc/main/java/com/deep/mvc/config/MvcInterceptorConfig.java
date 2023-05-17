package com.deep.mvc.config;

import cn.hutool.core.collection.ListUtil;
import com.deep.mvc.interceptor.Interceptor1;
import com.deep.mvc.interceptor.Interceptor2;
import com.deep.mvc.model.Model;
import org.apache.commons.collections.ListUtils;
import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/20 16:37
 */
@Configuration
public class MvcInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    Interceptor1 interceptor1;
    @Autowired
    Interceptor2 interceptor2;

    @Value("${server.port}")
    String port;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor1)
//            .excludePathPatterns("/error")
//            .addPathPatterns("/**");
//        registry.addInterceptor(interceptor2)
//            .excludePathPatterns("/error")
//            .addPathPatterns("/**");
        System.out.println(port);
    }

    public static void main(String[] args) {

        List<Model> list = ListUtil.of(
                new Model(1, "A"),
                new Model(1, "H"),
                new Model(2, "B"),
                new Model(2, "E"),
                new Model(2, "I"),
                new Model(3, "C"),
                new Model(3, "K"),
                new Model(3, "F"),
                new Model(4, "D"),
                new Model(4, "G"),
                new Model(4, "L"),
                new Model(5, "J")
        );

        Map<Integer, List<Model>> collect = list.stream().collect(Collectors.groupingBy(Model::getId));
        if (collect.size() > 1) {
            boolean isEnd = false;
            int index = 0;
            List<Model> tmp = new ArrayList<>();
            while (!isEnd) {
                isEnd = true;
                for (Map.Entry<Integer, List<Model>> entry : collect.entrySet()) {
                    if (entry.getValue().size() > index) {
                        tmp.add(entry.getValue().get(index));
                        isEnd = false;
                    }
                }
                ++index;
            }
            System.out.println(tmp);
        }

        Map<String, String> a = new HashMap<>();
        a.put("1","2");
        a.put("3","7");
        a.put("2","8");
        a.put("8","4");
        a.put("7","1");
        a.put("5","0");
        System.out.println(a.values());
    }

}