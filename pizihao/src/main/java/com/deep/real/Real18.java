package com.deep.real;

import java.util.Arrays;

/**
 * <h2>467. 环绕字符串中唯一的子字符串</h2>
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以s 看起来是这样的：
 * <p>
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 现在给定另一个字符串 p 。返回s 中唯一 的 p 的 非空子串的数量。
 *
 * @author Create by liuwenhao on 2022/5/25 16:03
 */
public class Real18 {

    public static void main(String[] args) {
        char a = 'a';
        char z = 'z';
        System.out.println((z - a) % 26);
        System.out.println(z);
        Real18 real18 = new Real18();
        System.out.println(real18.findSubstringInWraproundString("zaba"));
    }

    public int findSubstringInWraproundString(String p) {
        int[] ints = new int[26];
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                count = count + 1;
            } else {
                count = 1;
            }
            ints[p.charAt(i) - 97] = Math.max(ints[p.charAt(i) - 97], count);
        }

        return Arrays.stream(ints).sum();
    }
}