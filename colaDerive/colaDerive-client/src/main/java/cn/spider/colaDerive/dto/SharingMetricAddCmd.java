package cn.spider.colaDerive.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * SharingMetricAddCmd
 *
 * @author Frank Zhang
 * @date 2019-03-01 10:12 AM
 */
@Data
public class SharingMetricAddCmd extends CommonCommand{
    @NotNull
    private SharingMetricCO sharingMetricCO;
}
