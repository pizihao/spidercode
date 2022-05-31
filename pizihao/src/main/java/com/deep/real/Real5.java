package com.deep.real;

import java.util.Arrays;

/**
 * <h2>937. 重新排列日志文件</h2>
 * 给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
 * <p>
 * 有两种不同类型的日志：
 * <p>
 * 字母日志：除标识符之外，所有字均由小写字母组成
 * 数字日志：除标识符之外，所有字均由数字组成
 * 请按下述规则将日志重新排序：
 * <p>
 * 所有 字母日志 都排在 数字日志 之前。
 * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
 * 数字日志 应该保留原来的相对顺序。
 * 返回日志的最终顺序。
 *
 * @author Create by liuwenhao on 2022/5/3 19:19
 */
public class Real5 {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            //先把每个日志串的标志符去掉
            String[] s1 = log1.split(" ", 2);
            String[] s2 = log2.split(" ", 2);
            //判断除标识符外的第一个字符是数字true，字母false
            boolean isDigit1 = Character.isDigit(s1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(s2[1].charAt(0));
            //如果两个日志都是字母日志
            if (!isDigit1 && !isDigit2) {
                //先比较内容字母s1>s2则返回1，等于返0，小于返-1
                int cmp = s1[1].compareTo(s2[1]);
                if (cmp != 0) {
                    return cmp;
                }
                //若内容字母相同则比较标识符
                return s1[0].compareTo(s2[0]);
            }
            /*
             如果s1是数字字符，且s2是数字字符返回0，
             如果s1是数字字符，且s2是字母字符返回1，即s1>s2,从小到大排序，s2提前
             如果s1是字母字符，返回-1，
             */
            if (isDigit1) {
                return isDigit2 ? 0 : 1;
            }
            return -1;
        });
        return logs;
    }
}