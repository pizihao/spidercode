package com.deep.real;

/**
 * <h2>1021. 删除最外层的括号</h2>
 * 有效括号字符串为空 ""、"(" + A + ")"或A + B ，其中A 和B都是有效的括号字符串，+代表字符串的连接。
 * 例如，""，"()"，"(())()"和"(()(()))"都是有效的括号字符串。
 * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B的方法，我们称其为原语（primitive），其中A 和B都是非空有效括号字符串。
 * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中P_i是有效括号字符串原语。
 * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
 *
 * @author Create by liuwenhao on 2022/5/26 10:06
 */
public class Real19 {
    public static void main(String[] args) {
        Real19 real19 = new Real19();
        System.out.println(real19.removeOuterParentheses("(()(()))(()())()"));
    }

    public String removeOuterParentheses(String s) {
        StringBuilder builder = new StringBuilder();
        int origin = 0;
        for (char c : s.toCharArray()) {
            if (c == ')') {
                origin = origin - 1;
            }
            if (origin > 0) {
                builder.append(c);
            }
            if (c == '(') {
                origin = origin + 1;
            }
        }

        return builder.toString();
    }

    /**
     *         if ("".equals(s)) {
     *             return "";
     *         }
     *         Queue<Character> queue = new LinkedList<>();
     *         StringBuilder builder = new StringBuilder();
     *         int origin = 0;
     *         for (int i = 0; i < s.length(); i++) {
     *             char c = s.charAt(i);
     *             if (!queue.isEmpty() && queue.peek() == '(' && c == ')') {
     *                 queue.remove();
     *                 if (queue.isEmpty()) {
     *                     String substring = s.substring(origin + 1, i);
     *                     builder.append(substring);
     *                     origin = i + 1;
     *                 }
     *             } else {
     *                 queue.add(c);
     *             }
     *         }
     *
     *         return builder.toString();
     */
}