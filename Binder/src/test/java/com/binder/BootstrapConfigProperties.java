/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.binder;

import com.binder.AdapterExecutorProperties;

import java.util.List;
import java.util.Map;

/**
 * Bootstrap core properties.
 */
public class BootstrapConfigProperties  {

    public static final String PREFIX = "spring.dynamic.thread-pool";

    /**
     * Enable dynamic thread pool.
     */
    private Boolean enable = Boolean.TRUE;

    /**
     * Enabled banner.
     */
    private Boolean banner = Boolean.TRUE;

    /**
     * Thread pool monitoring related configuration.
     */
    private MonitorProperties monitor;

    @Deprecated
    private Boolean collect = Boolean.TRUE;

    @Deprecated
    private String collectType;

    @Deprecated
    private Long initialDelay = 10000L;

    @Deprecated
    private Long collectInterval = 5000L;

    /**
     * Config file type.
     */
    private ConfigFileTypeEnum configFileType;

    /**
     * Nacos config.
     */
    private Map<String, String> nacos;

    /**
     * Apollo config.
     */
    private Map<String, String> apollo;

    /**
     * Zookeeper config.
     */
    private Map<String, String> zookeeper;

    /**
     * etcd config
     */
    private Map<String, String> etcd;

    /**
     * Tomcat thread pool config.
     */
    private WebThreadPoolProperties tomcat;

    /**
     * Undertow thread pool config.
     */
    private WebThreadPoolProperties undertow;

    /**
     * Jetty thread pool config.
     * KeepAliveTime is not supported temporarily.
     */
    private WebThreadPoolProperties jetty;

    /**
     * Notify platforms.
     */
    private List<NotifyPlatformProperties> notifyPlatforms;

    /**
     * Check thread pool running status interval.
     */
    private Integer checkStateInterval;

    /**
     * Default dynamic thread pool configuration.
     */
    private ExecutorProperties defaultExecutor;

    /**
     * Dynamic thread pool configuration collection.
     */
    private List<ExecutorProperties> executors;

    /**
     * Tripartite framework thread pool adaptation set.
     */
    private List<AdapterExecutorProperties> adapterExecutors;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getBanner() {
        return banner;
    }

    public void setBanner(Boolean banner) {
        this.banner = banner;
    }

    public MonitorProperties getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorProperties monitor) {
        this.monitor = monitor;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    public Long getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Long initialDelay) {
        this.initialDelay = initialDelay;
    }

    public Long getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(Long collectInterval) {
        this.collectInterval = collectInterval;
    }

    public ConfigFileTypeEnum getConfigFileType() {
        return configFileType;
    }

    public void setConfigFileType(ConfigFileTypeEnum configFileType) {
        this.configFileType = configFileType;
    }

    public Map<String, String> getNacos() {
        return nacos;
    }

    public void setNacos(Map<String, String> nacos) {
        this.nacos = nacos;
    }

    public Map<String, String> getApollo() {
        return apollo;
    }

    public void setApollo(Map<String, String> apollo) {
        this.apollo = apollo;
    }

    public Map<String, String> getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(Map<String, String> zookeeper) {
        this.zookeeper = zookeeper;
    }

    public Map<String, String> getEtcd() {
        return etcd;
    }

    public void setEtcd(Map<String, String> etcd) {
        this.etcd = etcd;
    }

    public WebThreadPoolProperties getTomcat() {
        return tomcat;
    }

    public void setTomcat(WebThreadPoolProperties tomcat) {
        this.tomcat = tomcat;
    }

    public WebThreadPoolProperties getUndertow() {
        return undertow;
    }

    public void setUndertow(WebThreadPoolProperties undertow) {
        this.undertow = undertow;
    }

    public WebThreadPoolProperties getJetty() {
        return jetty;
    }

    public void setJetty(WebThreadPoolProperties jetty) {
        this.jetty = jetty;
    }

    public List<NotifyPlatformProperties> getNotifyPlatforms() {
        return notifyPlatforms;
    }

    public void setNotifyPlatforms(List<NotifyPlatformProperties> notifyPlatforms) {
        this.notifyPlatforms = notifyPlatforms;
    }

    public Integer getCheckStateInterval() {
        return checkStateInterval;
    }

    public void setCheckStateInterval(Integer checkStateInterval) {
        this.checkStateInterval = checkStateInterval;
    }

    public ExecutorProperties getDefaultExecutor() {
        return defaultExecutor;
    }

    public void setDefaultExecutor(ExecutorProperties defaultExecutor) {
        this.defaultExecutor = defaultExecutor;
    }

    public List<ExecutorProperties> getExecutors() {
        return executors;
    }

    public void setExecutors(List<ExecutorProperties> executors) {
        this.executors = executors;
    }

    public List<AdapterExecutorProperties> getAdapterExecutors() {
        return adapterExecutors;
    }

    public void setAdapterExecutors(List<AdapterExecutorProperties> adapterExecutors) {
        this.adapterExecutors = adapterExecutors;
    }

    @Override
    public String toString() {
        return "BootstrapConfigProperties{" +
                "enable=" + enable +
                ", banner=" + banner +
                ", monitor=" + monitor +
                ", collect=" + collect +
                ", collectType='" + collectType + '\'' +
                ", initialDelay=" + initialDelay +
                ", collectInterval=" + collectInterval +
                ", configFileType=" + configFileType +
                ", nacos=" + nacos +
                ", apollo=" + apollo +
                ", zookeeper=" + zookeeper +
                ", etcd=" + etcd +
                ", tomcat=" + tomcat +
                ", undertow=" + undertow +
                ", jetty=" + jetty +
                ", notifyPlatforms=" + notifyPlatforms +
                ", checkStateInterval=" + checkStateInterval +
                ", defaultExecutor=" + defaultExecutor +
                ", executors=" + executors +
                ", adapterExecutors=" + adapterExecutors +
                '}';
    }
}
