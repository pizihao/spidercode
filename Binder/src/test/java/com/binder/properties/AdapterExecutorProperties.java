package com.binder.properties;

import lombok.Data;

/**
 * Adapter executor properties.
 */
public class AdapterExecutorProperties {

    /**
     * Mark
     */
    private String mark;

    /**
     * Thread-pool key
     */
    private String threadPoolKey;

    /**
     * Core pool size
     */
    private Integer corePoolSize;

    /**
     * Maximum pool size
     */
    private Integer maximumPoolSize;

    /**
     * Nodes, application startup is not affect, change properties is effect
     */
    private String nodes;

    /**
     * these propertied is enabled?
     */
    private Boolean enable = true;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getThreadPoolKey() {
        return threadPoolKey;
    }

    public void setThreadPoolKey(String threadPoolKey) {
        this.threadPoolKey = threadPoolKey;
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

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "AdapterExecutorProperties{" +
                "mark='" + mark + '\'' +
                ", threadPoolKey='" + threadPoolKey + '\'' +
                ", corePoolSize=" + corePoolSize +
                ", maximumPoolSize=" + maximumPoolSize +
                ", nodes='" + nodes + '\'' +
                ", enable=" + enable +
                '}';
    }
}
