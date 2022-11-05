package cn.spider.colaDerive.command;

import com.alibaba.cola.dto.Response;
import cn.spider.colaDerive.domain.user.InfluenceMetric;
import cn.spider.colaDerive.domain.user.SharingMetric;
import cn.spider.colaDerive.domain.user.SharingMetricItem;
import cn.spider.colaDerive.domain.user.SharingScope;
import cn.spider.colaDerive.domain.user.UserProfile;
import cn.spider.colaDerive.dto.SharingMetricAddCmd;
import cn.spider.colaDerive.domain.user.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * SharingMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-02 5:00 PM
 */
@Component
public class SharingMetricAddCmdExe{

    @Resource
    private MetricGateway metricGateway;

    public Response execute(SharingMetricAddCmd cmd) {
        SharingMetricItem sharingMetricItem = new SharingMetricItem();
        BeanUtils.copyProperties(cmd.getSharingMetricCO(), sharingMetricItem);
        sharingMetricItem.setSubMetric(new SharingMetric(new InfluenceMetric(new UserProfile(cmd.getSharingMetricCO().getOwnerId()))));
        sharingMetricItem.setSharingScope(SharingScope.valueOf(cmd.getSharingMetricCO().getSharingScope()));
        metricGateway.save(sharingMetricItem);
        return Response.buildSuccess();
    }
}
