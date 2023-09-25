package cn.spider;

import java.util.HashSet;
import java.util.Set;

/**
 * 规则参数配置
 */
public class ParametersConfig {

    /**
     * 当条件名称前缀包含以下集合中的元素时，跳过
     */
    Set<String> spitConditionPrefix = new HashSet<>();

    /**
     * 当条件名称后缀包含以下集合中的元素时，跳过
     */
    Set<String> spitConditionSuffix = new HashSet<>();

    /**
     * 当条件名称子串存在以下集合中的元素时，跳过
     */
    Set<String> spitConditionSubstring = new HashSet<>();

    /**
     * 当操作名称前缀包含以下集合中的元素时，跳过
     */
    Set<String> spitActionPrefix = new HashSet<>();

    /**
     * 当操作名称后缀包含以下集合中的元素时，跳过
     */
    Set<String> spitActionSuffix = new HashSet<>();

    /**
     * 当操作名称子串存在以下集合中的元素时，跳过
     */
    Set<String> spitActionSubstring = new HashSet<>();

    /**
     * 是否打印异常堆栈开关
     */
    boolean printStack = false;

    /**
     * 当没有条件时，默认的条件返回
     */
    boolean emptyConditionResult = false;

    public void addConditionPrefix(String str) {
        this.spitConditionPrefix.add(str);
    }

    public void addConditionSuffix(String str) {
        this.spitConditionSuffix.add(str);
    }

    public void removeConditionSubstring(String str) {
        this.spitConditionSubstring.add(str);
    }

    public void removeConditionPrefix(String str) {
        this.spitConditionPrefix.remove(str);
    }

    public void removeConditionSuffix(String str) {
        this.spitConditionSuffix.remove(str);
    }

    public void addConditionSubstring(String str) {
        this.spitConditionSubstring.remove(str);
    }

    public void addActionPrefix(String str) {
        this.spitActionPrefix.add(str);
    }

    public void addActionSuffix(String str) {
        this.spitActionSuffix.add(str);
    }

    public void removeActionSubstring(String str) {
        this.spitActionSubstring.add(str);
    }

    public void removeActionPrefix(String str) {
        this.spitActionPrefix.remove(str);
    }

    public void removeActionSuffix(String str) {
        this.spitActionSuffix.remove(str);
    }

    public void addActionSubstring(String str) {
        this.spitActionSubstring.remove(str);
    }

    public void setPrintStack(boolean printStack) {
        this.printStack = printStack;
    }

    public void setEmptyConditionResult(boolean emptyConditionResult) {
        this.emptyConditionResult = emptyConditionResult;
    }

    public static ParametersConfig build() {
        return new ParametersConfig();
    }
}
