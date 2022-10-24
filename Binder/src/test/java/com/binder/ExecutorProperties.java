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

import com.binder.DynamicThreadPoolNotifyProperties;

/**
 * Executor properties.
 */
public class ExecutorProperties {

    /**
     * Thread pool id
     */
    private String threadPoolId;

    /**
     * Core pool size
     */
    private Integer corePoolSize;

    /**
     * Maximum pool size
     */
    private Integer maximumPoolSize;

    /**
     * Queue capacity
     */
    private Integer queueCapacity;

    /**
     * Blocking queue
     */
    private String blockingQueue;

    /**
     * Rejected handler
     */
    private String rejectedHandler;

    /**
     * Keep alive time
     */
    private Long keepAliveTime;

    /**
     * Execute timeout
     */
    private Long executeTimeOut;

    /**
     * Allow core thread timeout
     */
    private Boolean allowCoreThreadTimeOut;

    /**
     * Thread name prefix
     */
    private String threadNamePrefix;

    /**
     * Whether to enable thread pool running alarm
     */
    private Boolean alarm;

    /**
     * Active alarm
     */
    private Integer activeAlarm;

    /**
     * Capacity alarm
     */
    private Integer capacityAlarm;

    /**
     * Notify
     */
    private DynamicThreadPoolNotifyProperties notify;

    /**
     * Nodes, application startup is not affect, change properties is effect
     */
    private String nodes;

    public String getThreadPoolId() {
        return threadPoolId;
    }

    public void setThreadPoolId(String threadPoolId) {
        this.threadPoolId = threadPoolId;
    }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public String getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(String blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public String getRejectedHandler() {
        return rejectedHandler;
    }

    public void setRejectedHandler(String rejectedHandler) {
        this.rejectedHandler = rejectedHandler;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Long getExecuteTimeOut() {
        return executeTimeOut;
    }

    public void setExecuteTimeOut(Long executeTimeOut) {
        this.executeTimeOut = executeTimeOut;
    }

    public Boolean getAllowCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    public void setAllowCoreThreadTimeOut(Boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    public Boolean getAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public Integer getActiveAlarm() {
        return activeAlarm;
    }

    public void setActiveAlarm(Integer activeAlarm) {
        this.activeAlarm = activeAlarm;
    }

    public Integer getCapacityAlarm() {
        return capacityAlarm;
    }

    public void setCapacityAlarm(Integer capacityAlarm) {
        this.capacityAlarm = capacityAlarm;
    }

    public DynamicThreadPoolNotifyProperties getNotify() {
        return notify;
    }

    public void setNotify(DynamicThreadPoolNotifyProperties notify) {
        this.notify = notify;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "ExecutorProperties{" +
                "threadPoolId='" + threadPoolId + '\'' +
                ", corePoolSize=" + corePoolSize +
                ", maximumPoolSize=" + maximumPoolSize +
                ", queueCapacity=" + queueCapacity +
                ", blockingQueue='" + blockingQueue + '\'' +
                ", rejectedHandler='" + rejectedHandler + '\'' +
                ", keepAliveTime=" + keepAliveTime +
                ", executeTimeOut=" + executeTimeOut +
                ", allowCoreThreadTimeOut=" + allowCoreThreadTimeOut +
                ", threadNamePrefix='" + threadNamePrefix + '\'' +
                ", alarm=" + alarm +
                ", activeAlarm=" + activeAlarm +
                ", capacityAlarm=" + capacityAlarm +
                ", notify=" + notify +
                ", nodes='" + nodes + '\'' +
                '}';
    }
}
