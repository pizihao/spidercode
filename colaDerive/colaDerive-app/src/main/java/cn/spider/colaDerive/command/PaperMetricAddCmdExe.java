package cn.spider.colaDerive.command;

import com.alibaba.cola.dto.Response;
import cn.spider.colaDerive.domain.user.InfluenceMetric;
import cn.spider.colaDerive.domain.user.PaperMetric;
import cn.spider.colaDerive.domain.user.PaperMetricItem;
import cn.spider.colaDerive.domain.user.UserProfile;
import cn.spider.colaDerive.dto.PaperMetricAddCmd;
import cn.spider.colaDerive.domain.user.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PaperMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-03 11:41 AM
 */
@Component
public class PaperMetricAddCmdExe {

    @Autowired
    private MetricGateway metricGateway;

    public Response execute(PaperMetricAddCmd cmd) {
        PaperMetricItem paperMetricItem = new PaperMetricItem();
        BeanUtils.copyProperties(cmd.getPaperMetricCO(), paperMetricItem);
        paperMetricItem.setSubMetric(new PaperMetric(new InfluenceMetric(new UserProfile(cmd.getPaperMetricCO().getOwnerId()))));
        paperMetricItem.setMetricOwner(new UserProfile(cmd.getPaperMetricCO().getOwnerId()));
        metricGateway.save(paperMetricItem);

        return Response.buildSuccess();
    }
}