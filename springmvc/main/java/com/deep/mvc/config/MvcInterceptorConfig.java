package com.deep.mvc.config;

import com.deep.mvc.interceptor.Interceptor1;
import com.deep.mvc.interceptor.Interceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor1)
            .excludePathPatterns("/error")
            .addPathPatterns("/**");
        registry.addInterceptor(interceptor2)
            .excludePathPatterns("/error")
            .addPathPatterns("/**");
    }


}