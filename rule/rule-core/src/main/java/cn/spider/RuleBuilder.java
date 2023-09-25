package cn.spider;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {

    private String name;
    private int order = Integer.MAX_VALUE;
    private List<Condition> conditions = new ArrayList<>();
    private List<Action> actions;

    public RuleBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RuleBuilder order(int order) {
        this.order = order;
        return this;
    }

    public RuleBuilder when(List<Condition> conditions) {
        this.conditions = conditions;
        return this;
    }

    public RuleBuilder whenAddAll(List<Condition> conditions) {
        this.conditions.addAll(conditions);
        return this;
    }

    public RuleBuilder whenAdd(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public RuleBuilder then(List<Action> actions) {
        this.actions = actions;
        return this;
    }

    public Rule build() {
        return new DefinitionRule(name, order, conditions, actions);
    }

}
