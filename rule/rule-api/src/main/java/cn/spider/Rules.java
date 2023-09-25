package cn.spider;

import java.util.*;

/**
 * 由一组规则组成的规则集
 */
public class Rules {

    /**
     * 规则集合
     */
    Set<Rule> ruleSet = new HashSet<>();

    public Rules(Set<Rule> rules) {
        this.ruleSet = new TreeSet<>(rules);
    }

    public Rules(Rule... rules) {
        Collections.addAll(this.ruleSet, rules);
    }

    /**
     * 注册一组规则到规则中
     *
     * @param rules 待注册的规则
     */
    public void register(Rule... rules) {
        Objects.requireNonNull(rules);
        for (Rule rule : rules) {
            Objects.requireNonNull(rule);
            this.ruleSet.add(rule);
        }
    }

    /**
     * 注销一组规则
     *
     * @param rules 待注销的规则
     */
    public void unregister(Rule... rules) {
        Objects.requireNonNull(rules);
        for (Rule rule : rules) {
            Objects.requireNonNull(rule);
            this.ruleSet.remove(rule);
        }
    }

    /**
     * 通过名称注销一组规则
     *
     * @param ruleName 规则名称
     */
    public void unregister(final String ruleName) {
        Objects.requireNonNull(ruleName);
        Rule rule = getRule(ruleName);
        if (rule != null) {
            unregister(rule);
        }
    }

    private Rule getRule(String ruleName) {
        return ruleSet.stream()
                .filter(rule -> rule.name().equalsIgnoreCase(ruleName))
                .findFirst()
                .orElse(null);
    }


    public boolean isEmpty() {
        return ruleSet.isEmpty();
    }

    public int size() {
        return ruleSet.size();
    }
}
