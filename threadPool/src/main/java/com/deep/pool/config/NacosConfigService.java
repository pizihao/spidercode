package com.deep.pool.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/31 12:33
 */
@Configuration
public class NacosConfigService {

    @Autowired
    NacosConfigProperties nacosConfigProperties;

    @Bean
    public ConfigService configService() throws NacosException {
        return NacosFactory.createConfigService(nacosConfigProperties.getConfigServiceProperties());

    }
}