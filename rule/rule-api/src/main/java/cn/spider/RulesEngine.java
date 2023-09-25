package cn.spider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 规则引擎
 */
public interface RulesEngine {

    /**
     * 获取规则的配置参数
     *
     * @return 规则配置参数
     */
    ParametersConfig getConfig();

    /**
     * 获取所有的规则执行监听器
     *
     * @return 规则执行监听器集合
     */
    default List<RuleListener> getRuleListeners() {
        return Collections.emptyList();
    }

    /**
     * 获取所有的规则引擎执行监听器
     *
     * @return 规则引擎执行监听器
     */
    default List<RuleEngineListener> getRuleEngineListeners() {
        return Collections.emptyList();
    }

    /**
     * 评估和执行所有的规则
     */
    void executionRules(Models models, Rule rule);

}
