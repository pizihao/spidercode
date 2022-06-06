package com.deep.real;

/**
 * <h2>541. 反转字符串 II</h2>
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * @author Create by liuwenhao on 2022/6/5 15:16
 */
public class Real28 {
    public String reverseStr(String s, int k) {
        int length = s.length();
        // 代表可以正常循环的次数
        int loop = length / (2 * k);
        // 最终剩余的
        int surplus = length % (2 * k);
        StringBuilder builder = new StringBuilder();
        // loop每次自增1代表推进一个2k
        int beginIndex = 0;
        int endIndex = beginIndex + k;
        for (int i = 1; i <= loop; i++) {
            // 找到这一组的前k个
            builder.append(new StringBuilder(s.substring(beginIndex, endIndex)).reverse())
                .append(s, endIndex, endIndex + k);
            beginIndex = endIndex + k;
            endIndex = beginIndex + k;
        }
        if (surplus < k) {
            builder.append(new StringBuilder(s.substring(beginIndex)).reverse());
        } else {
            builder.append(new StringBuilder(s.substring(beginIndex, endIndex)).reverse())
                .append(s.substring(endIndex));
        }
        return builder.toString();
    }
}