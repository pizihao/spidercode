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
@RequestMapping("test")
public class TestController {

    @Autowired
    ThreadPoolExecutor messageProduceDynamicExecutor;
    @Autowired
    ThreadPoolExecutor messageConsumeDynamicExecutor;

    @GetMapping()
    public void testPut() {
        System.out.println("===============messageProduceDynamicExecutor====================");
        System.out.println(messageProduceDynamicExecutor.getCorePoolSize());
        System.out.println(messageProduceDynamicExecutor.getMaximumPoolSize());
        System.out.println(messageProduceDynamicExecutor.getQueue().size());
        System.out.println("===============messageProduceDynamicExecutor====================");
        System.out.println("===============messageConsumeDynamicExecutor====================");
        System.out.println(messageConsumeDynamicExecutor.getCorePoolSize());
        System.out.println(messageConsumeDynamicExecutor.getMaximumPoolSize());
        System.out.println(messageConsumeDynamicExecutor.getQueue().size());
        System.out.println("===============messageConsumeDynamicExecutor====================");
    }


}