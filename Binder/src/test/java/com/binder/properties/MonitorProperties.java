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

package com.binder.properties;

/**
 * Thread pool monitoring properties.
 */
public class MonitorProperties {

    /**
     * Collect thread pool runtime indicators.
     */
    private Boolean enable = Boolean.TRUE;

    /**
     * Type of collection thread pool running data. eg: log,micrometer. Multiple can be used at the same time.
     */
    private String collectTypes;

    /**
     * Monitor the type of thread pool. eg: dynamic,web,adapter. Can be configured arbitrarily, default dynamic.
     */
    private String threadPoolTypes = MonitorThreadPoolTypeEnum.DYNAMIC.toString().toLowerCase();

    /**
     * Delay starting data acquisition task. unit: ms
     */
    private Long initialDelay = 10000L;

    /**
     * Collect interval. unit: ms
     */
    private Long collectInterval = 5000L;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getCollectTypes() {
        return collectTypes;
    }

    public void setCollectTypes(String collectTypes) {
        this.collectTypes = collectTypes;
    }

    public String getThreadPoolTypes() {
        return threadPoolTypes;
    }

    public void setThreadPoolTypes(String threadPoolTypes) {
        this.threadPoolTypes = threadPoolTypes;
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

    @Override
    public String toString() {
        return "MonitorProperties{" +
                "enable=" + enable +
                ", collectTypes='" + collectTypes + '\'' +
                ", threadPoolTypes='" + threadPoolTypes + '\'' +
                ", initialDelay=" + initialDelay +
                ", collectInterval=" + collectInterval +
                '}';
    }
}
