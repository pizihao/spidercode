package cn.spider;


import java.util.Collections;
import java.util.List;

public class DefinitionRule implements Rule {

    private final String name;
    private final int order;
    private final List<Condition> conditions;
    private final List<Action> actions;

    public DefinitionRule(String name) {
        this(name, Integer.MAX_VALUE);
    }

    public DefinitionRule(String name, int order) {
        this(name, order, Collections.emptyList(), Collections.emptyList());
    }

    public DefinitionRule(String name, int order, List<Condition> conditions, List<Action> actions) {
        this.name = name;
        this.order = order;
        this.conditions = conditions;
        this.actions = actions;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int order() {
        return order;
    }

    @Override
    public boolean isConditionEmpty() {
        return conditions.isEmpty();
    }

    @Override
    public boolean evaluate(Models models) {
        return conditions.stream()
                .allMatch(c -> c.evaluate(models));
    }

    @Override
    public void execute(Models models) {
        for (Action action : actions) {
            action.execute(models);
        }
    }
}
