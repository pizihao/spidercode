package com.deep.pool.conrtroller;

import cn.hippo4j.core.toolkit.inet.InetUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/14 14:12
 */

@RestController
@RequestMapping("Test")
public class TestController {

    @Autowired
    ThreadPoolExecutor messageProduceDynamicExecutor;
    @Autowired
    InetUtils inetUtils;

    @PutMapping("/{id}")
    public Model testPut(@RequestBody Model model, @PathVariable Integer id) {

        System.out.println(messageProduceDynamicExecutor.getCorePoolSize());
        InetUtils.HostInfo hostInfo = inetUtils.findFirstNonLoopbackHostInfo();
        System.out.println(hostInfo.getHostname());
        System.out.println(hostInfo.getIpAddress());
        System.out.println(hostInfo.getIpAddressAsInt());
        System.out.println();
        model.setId(id);
        return model;
    }


    public static void main(String[] args) {



    }

    public static <T> T mapToBean(Map<String, Object> map, T bean)   {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }


}