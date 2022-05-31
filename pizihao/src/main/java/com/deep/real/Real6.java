package com.deep.real;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>1417. 重新格式化字符串</h2>
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 *
 * @author Create by liuwenhao on 2022/5/3 20:16
 */
public class Real6 {

    public static void main(String[] args) {
        Real6 real6 = new Real6();
        System.out.println(real6.reformat("covid2019"));
    }

    public String reformat(String s) {
        // 将s的每一位按照格式区分
        List<Character> chars1 = new ArrayList<>();
        List<Character> chars2 = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean digit = Character.isDigit(c);
            if (digit) {
                chars1.add(c);
            } else {
                chars2.add(c);
            }
        }
        if (Math.abs(chars1.size() - chars2.size()) >= 2) {
            return "";
        }
        int index = s.length() / 2;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < index; i++) {
            if (chars1.size() > chars2.size()) {
                builder.append(chars1.get(i)).append(chars2.get(i));
            } else {
                builder.append(chars2.get(i)).append(chars1.get(i));
            }
        }
        if (chars1.size() > chars2.size()) {
            builder.append(chars1.get(index));
        } else if (chars2.size() > chars1.size()) {
            builder.append(chars2.get(index));
        }
        return builder.toString();
    }
}