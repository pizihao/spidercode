package com.deep.real;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>081. 允许重复选择元素的组合</h2>
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 *
 * @author Create by liuwenhao on 2022/5/28 17:21
 */
public class Real23 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    public void dfs(List<List<Integer>> list, List<Integer> one, int[] candidates, int target, int p) {
        if (target <= 0) {
            // 只有等于是符合情况的
            if (target == 0) {
                list.add(new ArrayList<>(one));
            }
            return;
        }
        for (int i = p; i < candidates.length; i++) {
            one.add(candidates[i]);
            // 穷举所有的情况，从p开始，因为数组中的数据是可选的
            dfs(list, one, candidates, target - candidates[i], i);
            one.remove(one.size() - 1);
        }
    }
}