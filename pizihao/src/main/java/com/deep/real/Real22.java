package com.deep.real;

import java.util.Arrays;

/**
 * <h2>881. 救生艇</h2>
 * 给定数组people。people[i]表示第 i个人的体重，船的数量不限，每艘船可以承载的最大重量为limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
 * 返回 承载所有人所需的最小船数。
 *
 * @author Create by liuwenhao on 2022/5/28 16:58
 */
public class Real22 {
    public static void main(String[] args) {
        Real22 real22 = new Real22();
        System.out.println(real22.numRescueBoats(new int[]{1, 2, 4, 5}, 6));
    }

    public int numRescueBoats(int[] people, int limit) {

        int left = 0;
        int right = people.length - 1;
        int count = 0;
        Arrays.sort(people);
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                count = count + 1;
                left++;
            }
            right--;
        }
        return count + (people.length - count * 2);
    }
}