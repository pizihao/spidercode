package cn.spider.colaDerive.dto;

import com.alibaba.cola.event.DomainEventI;
import lombok.Data;

@Data
public class MetricItemCreatedEvent implements DomainEventI {

    private String id;

    private String userId;

    private String mainMetricType;
}
