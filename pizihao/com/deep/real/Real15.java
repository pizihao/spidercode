package deep.real;

import java.util.Arrays;

/**
 * <h2>961. 在长度 2N 的数组中找出重复 N 次的元素</h2>
 * 给你一个整数数组 nums ，该数组具有以下属性：
 * <p>
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 *
 * @author Create by liuwenhao on 2022/5/21 15:27
 */
public class Real15 {
    public int repeatedNTimes(int[] nums) {
        int max = Arrays.stream(nums).max().orElse(0);
        int[] arr = new int[max + 1];
        for (int num : nums) {
            arr[num] = arr[num] + 1;
            if (arr[num] > 0){
                return num;
            }
        }
        return 0;
    }
}