package com.example.druidtest.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.util.Assert;

public class IdWorkerUtil {

    private static IdWorker idWorker = null;

    public IdWorkerUtil() {
    }

    private static Long getId(Long dockerId) {
        if (idWorker == null) {
            syncInit(dockerId);
        }

        return idWorker.nextId();
    }

    public static Long getId() {
        String ipAddr = IpUtil.getLocalIP();
        Assert.isTrue(StringUtils.isNotEmpty(ipAddr), "ID生成器未读取到本机IP地址");
        Long dockerId = getDockerId(ipAddr);
        return getId(dockerId);
    }

    private static Long getDockerId(String ipaddr) {
        if (StringUtils.isEmpty(ipaddr)) {
            return null;
        } else {
            String[] ipSegments = ipaddr.split("\\.");
            String s1 = Integer.toHexString(Integer.valueOf(ipSegments[2]));
            String s2 = Integer.toHexString(Integer.valueOf(ipSegments[3]));
            s2 = s2.length() == 1 ? "0" + s2 : s2;
            String hex = s1 + s2;
            Long dockerId = Long.parseLong(hex, 16);
            return dockerId;
        }
    }

    private static synchronized void syncInit(Long dockerId) {
        if (idWorker == null) {
            idWorker = new IdWorker(dockerId);
        }

    }

}
