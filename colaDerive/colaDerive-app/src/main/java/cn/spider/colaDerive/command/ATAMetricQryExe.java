package cn.spider.colaDerive.command;

import com.alibaba.cola.dto.MultiResponse;
import cn.spider.colaDerive.domain.user.SubMetricType;
import cn.spider.colaDerive.dto.ATAMetricQry;
import cn.spider.colaDerive.dto.ATAMetricCO;
import cn.spider.colaDerive.gatewayimpl.rpc.MetricMapper;
import cn.spider.colaDerive.gatewayimpl.rpc.MetricDO;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ATAMetricQryExe{

    @Resource
    private MetricMapper metricMapper;

    public MultiResponse<ATAMetricCO> execute(ATAMetricQry cmd) {
        List<MetricDO> metricDOList = metricMapper.listBySubMetric(cmd.getOwnerId(), SubMetricType.ATA.getMetricSubTypeCode());
        List<ATAMetricCO> ataMetricCOList = new ArrayList<>();
        metricDOList.forEach(metricDO -> {
            ATAMetricCO ataMetricCO = JSON.parseObject(metricDO.getMetricItem(), ATAMetricCO.class);
            ataMetricCO.setOwnerId(metricDO.getUserId());
            ataMetricCOList.add(ataMetricCO);
        });
        return MultiResponse.ofWithoutTotal(ataMetricCOList);
    }

}
