package cn.spider;

/**
 * 操作
 */
public interface Action extends Named, Order {

    /**
     * 执行评估确定后的操作
     *
     * @param model 事实模型
     */
    void execute(Models model);

}
