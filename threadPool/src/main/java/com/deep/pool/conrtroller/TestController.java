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
        System.out.println(toSymbolCase("ContratId", '_'));
    }

    public static <T> T mapToBean(Map<String, Object> map, T bean)   {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static String toSymbolCase(CharSequence str, char symbol) {
        if (str == null) {
            return null;
        }

        final int length = str.length();
        final StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < length; i++) {
            c = str.charAt(i);
            final Character preChar = (i > 0) ? str.charAt(i - 1) : null;
            if (Character.isUpperCase(c)) {
                final Character nextChar = (i < str.length() - 1) ? str.charAt(i + 1) : null;
                if (null != preChar && Character.isUpperCase(preChar)) {
                    sb.append(c);
                } else if (null != nextChar && Character.isUpperCase(nextChar)) {
                    if (null != preChar && symbol != preChar) {
                        sb.append(symbol);
                    }
                    sb.append(c);
                } else {
                    if (null != preChar && symbol != preChar) {
                        sb.append(symbol);
                    }
                    sb.append(Character.toLowerCase(c));
                }
            } else {
                if (sb.length() > 0 && Character.isUpperCase(sb.charAt(sb.length() - 1)) && symbol != c) {
                    sb.append(symbol);
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
}