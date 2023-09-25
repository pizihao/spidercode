package cn.spider;

/**
 * 条件
 */
public interface Condition extends Named, Order {

    /**
     * 对当前条件进行评估，并给出评估结果
     *
     * @param model 事实模型
     * @return 评估结果
     */
    boolean evaluate(Models model);

}
