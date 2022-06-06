package com.deep.real;

/**
 * <h2>796. 旋转字符串</h2>
 * 给定两个字符串, s和goal。如果在若干次旋转操作之后，s能变成goal，那么返回true。
 * s的 旋转操作 就是将s 最左边的字符移动到最右边。
 * 例如, 若s = 'abcde'，在旋转一次之后结果就是'bcdea'。
 *
 * @author Create by liuwenhao on 2022/5/31 17:45
 */
public class Real24 {
    public static void main(String[] args) {

        Real24 real24 = new Real24();
        System.out.println(real24.rotateString("ohbrwzxvxe", "uornhegseo"));

    }

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        String a = s + s;
        return a.contains(goal);
    }
}