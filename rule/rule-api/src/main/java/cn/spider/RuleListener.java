package cn.spider;

/**
 * 规则执行监听器
 */
public interface RuleListener {

    /**
     * 评估之前执行，可用于过滤
     *
     * @param rule   规则
     * @param models 事实模型
     * @return 如果可评估规则，则为true，否则为false
     */
    default boolean beforeEvaluation(Rule rule, Models models) {
        return true;
    }

    /**
     * 评估之后执行
     *
     * @param rule   规则
     * @param models 事实模型
     * @param result 评估结果
     */
    default void afterEvaluation(Rule rule, Models models, boolean result) {
    }

    /**
     * 规则评估错误后触发。
     *
     * @param rule      规则
     * @param models    事实模型
     * @param exception 异常信息
     */
    default void onError(Rule rule, Models models, Exception exception) {
    }

    /**
     * 操纵执行前触发
     *
     * @param rule   规则
     * @param models 事实模型
     */
    default void beforeExecute(Rule rule, Models models) {
    }

    /**
     * 在成功执行后触发
     *
     * @param rule   规则
     * @param models 事实模型
     */
    default void onSuccess(Rule rule, Models models) {
    }

    /**
     * 执行异常后触发
     *
     * @param rule      规则
     * @param models    事实模型
     * @param exception 异常信息
     */
    default void onFailure(Rule rule, Models models, Exception exception) {
    }


}
