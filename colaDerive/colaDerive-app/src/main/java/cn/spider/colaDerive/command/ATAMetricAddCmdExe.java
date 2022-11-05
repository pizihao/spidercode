package cn.spider.colaDerive.command;

import com.alibaba.cola.dto.Response;
import cn.spider.colaDerive.domain.user.ATAMetric;
import cn.spider.colaDerive.domain.user.ATAMetricItem;
import cn.spider.colaDerive.domain.user.InfluenceMetric;
import cn.spider.colaDerive.domain.user.UserProfile;
import cn.spider.colaDerive.dto.ATAMetricAddCmd;
import cn.spider.colaDerive.domain.user.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ATAMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-01 11:42 AM
 */
@Component
public class ATAMetricAddCmdExe{

    @Autowired
    private MetricGateway metricGateway;

    public Response execute(ATAMetricAddCmd cmd) {
        ATAMetricItem ataMetricItem = new ATAMetricItem();
        BeanUtils.copyProperties(cmd.getAtaMetricCO(), ataMetricItem);
        ataMetricItem.setSubMetric(new ATAMetric(new InfluenceMetric(new UserProfile(cmd.getAtaMetricCO().getOwnerId()))));
        metricGateway.save(ataMetricItem);
        return Response.buildSuccess();
    }
}
