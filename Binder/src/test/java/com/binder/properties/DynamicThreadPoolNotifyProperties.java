package com.binder.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dynamic thread-pool notify properties.
 */
public class DynamicThreadPoolNotifyProperties {

    /**
     * Thread pool run alarm interval. unit: s
     */
    private Integer interval;

    /**
     * Receives
     */
    private String receives;

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getReceives() {
        return receives;
    }

    public void setReceives(String receives) {
        this.receives = receives;
    }

    @Override
    public String toString() {
        return "DynamicThreadPoolNotifyProperties{" +
                "interval=" + interval +
                ", receives='" + receives + '\'' +
                '}';
    }
}
