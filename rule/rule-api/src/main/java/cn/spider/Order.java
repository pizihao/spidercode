package cn.spider;

/**
 * 排序，定义可持有顺序的实体
 */
public interface Order extends Comparable<Order> {

    /**
     * 排名，位次
     *
     * @return 排名大小
     */
    default int order() {
        return Integer.MAX_VALUE;
    }

    @Override
    default int compareTo(Order o) {
        return this.order() - o.order();
    }
}
