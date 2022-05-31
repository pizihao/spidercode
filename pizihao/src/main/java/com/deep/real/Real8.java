package com.deep.real;

/**
 * <h2>1876. 长度为三且各字符不同的子字符串</h2>
 * 如果一个字符串不含有任何重复字符，我们称这个字符串为 好字符串。
 * 给你一个字符串 s，请你返回 s中长度为 3的 好子字符串 的数量。
 * 注意，如果相同的好子字符串出现多次，每一次都应该被记入答案之中。
 * 子字符串 是一个字符串中连续的字符序列。
 *
 * @author Create by liuwenhao on 2022/5/4 19:10
 */
public class Real8 {
    public static void main(String[] args) {

    }

    public int countGoodSubstrings(String s) {
        int number = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            char first = s.charAt(i);
            char second = s.charAt(i + 1);
            char third = s.charAt(i + 2);
            if (first != second && first != third && second != third) {
                number++;
            }
        }
        return number;
    }
}