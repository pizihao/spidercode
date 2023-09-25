package cn.spider;

/**
 * 规则
 */
public interface Rule extends Named, Order {

    /**
     * 条件是否为空
     *
     * @return boolean
     */
    boolean isConditionEmpty();

    /**
     * 评估一组事实模型
     *
     * @param models 事实模型
     * @return 评估结果
     */
    boolean evaluate(Models models);

    /**
     * 根据一组事实执行规则中的操作
     *
     * @param models 事实模型
     */
    default void execute(Models models) {
    }

}
