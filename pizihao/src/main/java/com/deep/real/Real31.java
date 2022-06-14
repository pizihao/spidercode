package com.deep.real;

/**
 * <h2>1750. 删除字符串两端相同字符后的最短长度</h2>
 * 给你一个只包含字符 'a'，'b' 和 'c' 的字符串 s ，你可以执行下面这个操作（5 个步骤）任意次：
 * <p>
 * 选择字符串 s 一个 非空 的前缀，这个前缀的所有字符都相同。
 * 选择字符串 s 一个 非空 的后缀，这个后缀的所有字符都相同。
 * 前缀和后缀在字符串中任意位置都不能有交集。
 * 前缀和后缀包含的所有字符都要相同。
 * 同时删除前缀和后缀。
 * 请你返回对字符串 s 执行上面操作任意次以后（可能 0 次），能得到的 最短长度 。
 *
 * @author Create by liuwenhao on 2022/6/14 16:20
 */
public class Real31 {
    public int minimumLength(String s) {
        int length = s.length();
        int left = 0;
        int right = length - 1;

        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if ( l != r) {
                break;
            }

            // 找打第一个和最后一个连续相同的
            while (left < right && l == s.charAt(left + 1)) {
                left++;
            }
            while (left < right && r == s.charAt(right - 1)) {
                right--;
            }

            left++;
            right--;
        }
        return Math.max(right - left + 1, 0);

    }
}