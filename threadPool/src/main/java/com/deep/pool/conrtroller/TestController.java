package com.deep.pool.conrtroller;

import cn.hippo4j.core.toolkit.inet.InetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
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


}