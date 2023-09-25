package cn.spider;

/**
 * 规则引擎监听器
 */
public interface RuleEngineListener {

    /**
     * 再评估规则引擎操作前触发
     *
     * @param rules  规则集
     * @param models 事实模型
     */
    default void beforeEvaluate(Rules rules, Models models) {
    }

    /**
     * 再执行规则引擎操作后触发
     *
     * @param rules  规则集
     * @param models 事实模型
     */
    default void afterExecute(Rules rules, Models models) {
    }

}
