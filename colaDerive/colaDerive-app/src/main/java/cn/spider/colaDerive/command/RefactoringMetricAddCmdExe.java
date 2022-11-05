package cn.spider.colaDerive.command;

import com.alibaba.cola.dto.Response;
import cn.spider.colaDerive.domain.user.ContributionMetric;
import cn.spider.colaDerive.domain.user.RefactoringLevel;
import cn.spider.colaDerive.domain.user.RefactoringMetric;
import cn.spider.colaDerive.domain.user.RefactoringMetricItem;
import cn.spider.colaDerive.domain.user.UserProfile;
import cn.spider.colaDerive.dto.RefactoringMetricAddCmd;
import cn.spider.colaDerive.domain.user.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RefactoringMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-04 11:15 AM
 */
@Component
public class RefactoringMetricAddCmdExe{

    @Autowired
    private MetricGateway metricGateway;

    public Response execute(RefactoringMetricAddCmd cmd) {
        RefactoringMetricItem refactoringMetricItem = new RefactoringMetricItem();
        BeanUtils.copyProperties(cmd.getRefactoringMetricCO(), refactoringMetricItem);
        refactoringMetricItem.setSubMetric(new RefactoringMetric(new ContributionMetric(new UserProfile(cmd.getRefactoringMetricCO().getOwnerId()))));
        refactoringMetricItem.setRefactoringLevel(RefactoringLevel.valueOf(cmd.getRefactoringMetricCO().getRefactoringLevel()));
        metricGateway.save(refactoringMetricItem);
        return Response.buildSuccess();
    }
}
