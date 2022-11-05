package cn.spider.colaDerive.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * RefactoringMetricAddCmd
 *
 * @author Frank Zhang
 * @date 2019-03-04 11:04 AM
 */
@Data
public class RefactoringMetricAddCmd extends CommonCommand{
    @NotNull
    private RefactoringMetricCO refactoringMetricCO;
}
