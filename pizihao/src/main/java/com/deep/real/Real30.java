package com.deep.real;

import java.util.Arrays;

/**
 * 532. 数组中的 k-diff 数对
 * 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * 注意，|val| 表示 val 的绝对值。
 *
 * @author Create by liuwenhao on 2022/6/16 16:33
 */
public class Real30 {

    public int findPairs(int[] nums, int k) {
        int n = nums.length, res = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n - 1 && j < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while (j <= i || (j < n && (nums[j] < nums[i] + k))) {
                j++;
            }
            if (j < n && nums[j] == nums[i] + k) {
                res++;
            }
        }
        return res;
    }

}