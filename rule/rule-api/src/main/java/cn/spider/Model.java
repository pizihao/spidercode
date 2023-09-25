package cn.spider;

import java.util.Objects;

/**
 * 事实模型
 */
public class Model<T> implements Named {

    /**
     * 唯一ID，当两个事实模型的name相同时，则任务两个事实模型为同一个事实模型
     */
    String name;

    /**
     * 事实模型内部的具体对象，为null时对应的条件全部为false
     */
    T t;

    public Model(String name, T t) {
        Objects.requireNonNull(name);
        this.name = name;
        this.t = t;
    }

    /**
     * 获取当前事实模型的具体类型
     *
     * @return 对象类型
     */
    T getObj() {
        return t;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model<?> model = (Model<?>) o;
        return Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
