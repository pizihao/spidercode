package com.deep.real;

/**
 * <h2>剑指 Offer II 072. 求平方根</h2>
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。
 * 正数的平方根有两个，只输出其中的正数平方根。
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 *
 * @author Create by liuwenhao on 2022/6/20 11:27
 */
public class Real32 {
    // 二分查找：
    //可以从一个数的二分之一开始找，平方后和x比较，如果x大则选择x/2 和x的中间数，如果x小则选择0和x/2中间得数，重复以上过程
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}