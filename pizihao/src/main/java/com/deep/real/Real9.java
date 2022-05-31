package com.deep.real;

/**
 * <h2>713. 乘积小于 K 的子数组</h2>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *
 * @author Create by liuwenhao on 2022/5/5 11:31
 */
public class Real9 {

    public static void main(String[] args) {

    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1;
        int count = 0;

        int left = 0;
        int right = 0;
        // 右指针从左向右滑动
        while (right < nums.length) {
            // 记录每次的乘积
            product *= nums[right++];
            while (left < right && product >= k) {
                // 窗口头滑动，除去窗口的初始值
                product /= nums[left++];
            }
            count += (right - left);
        }

        return count;
    }
}