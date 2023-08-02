package com.example.druidtest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpUtil {

    private static final Logger LOG = LoggerFactory.getLogger(IpUtil.class);
    private static final String WINDOWS = "windows";
    private static final int SEGMENT_MAX_LEN = 255;

    public IpUtil() {
    }

    public static String getLocalIP() {
        if (isWindowsOS()) {
            String ipAddr = null;

            try {
                ipAddr = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException var2) {
                LOG.error("获取本机IP地址异常", var2);
            }

            return ipAddr;
        } else {
            return getLinuxLocalIp();
        }
    }

    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }

        return isWindowsOS;
    }

    public static String getLocalHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    private static String getLinuxLocalIp() {
        String ip = null;

        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

            while(true) {
                NetworkInterface intf;
                String name;
                do {
                    do {
                        if (!en.hasMoreElements()) {
                            return ip;
                        }

                        intf = (NetworkInterface)en.nextElement();
                        name = intf.getName();
                    } while(name.contains("docker"));
                } while(name.contains("lo"));

                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();

                while(enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                            ip = ipaddress;
                        }
                    }
                }
            }
        } catch (SocketException var7) {
            LOG.error("获取主机IP地址异常", var7);
            return ip;
        }
    }

}
