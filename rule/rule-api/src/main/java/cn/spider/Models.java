package cn.spider;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 事实模型集合
 */
public class Models {

    /**
     * 事实模型集合
     */
    Set<Model<?>> modelSet = new HashSet<>();

    public Models(Set<Model<?>> modelSet) {
        this.modelSet = modelSet;
    }

    public Models() {
    }

    /**
     * 添加事实模型到集合中，如果已存在则替换
     *
     * @param model 事实模型
     */
    public void add(Model<?> model) {
        Objects.requireNonNull(model);
        Model<?> m = getModel(model.name);
        if (Objects.nonNull(m)) {
            modelSet.remove(m);
        }
        modelSet.add(model);
    }

    /**
     * 添加事实模型到集合中，如果已存在则替换
     *
     * @param name 名称
     * @param t    事实模型值
     */
    public <T> void add(String name, T t) {
        Objects.requireNonNull(name);
        Model<?> m = getModel(name);
        if (Objects.nonNull(m)) {
            modelSet.remove(m);
        }
        modelSet.add(new Model<>(name, t));
    }

    /**
     * 根据名称获取事件模型的具体值，如果没有则为null
     *
     * @param name 名称
     */
    public <T> T get(String name) {
        Objects.requireNonNull(name);
        Model<T> m = getModel(name);
        if (Objects.nonNull(m)) {
            return m.getObj();
        }
        return null;
    }


    /**
     * 通过名称获取事实模型
     *
     * @param modelName 事实模型名称
     * @return Model
     */
    @SuppressWarnings("unchecked")
    public <T> Model<T> getModel(String modelName) {
        Objects.requireNonNull(modelName);
        return (Model<T>) modelSet.stream()
                .filter(m -> Objects.equals(m.name, modelName))
                .findFirst()
                .orElse(null);
    }


    public void clear() {
        modelSet.clear();
    }
}
