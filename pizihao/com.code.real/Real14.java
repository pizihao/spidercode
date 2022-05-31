import java.util.Arrays;

/**
 * <h2>最小移动次数两数相等</h2>
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * <p>
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 *
 * @author Create by liuwenhao on 2022/5/19 21:32
 */
public class Real14 {

    public static void main(String[] args) {


    }

    public int minMoves2(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        /*
            nums 排序后位于中间的数
            奇数:nums[nums.length/2]
            偶数:偶数只需要是中间两个数之间任意一数即可：
            比如：1,3,7,10 位于中间的数值是3,4,5,6,7。无论最终是那种，移动的总次数都是13。
            所以偶数的中间数:nums[nums.length/2]，在自动去掉余数的情况下和奇数相同
        */
        int middle = nums[nums.length / 2];
        for (int num : nums) {
            count = count + Math.abs(num - middle);
        }

        return count;
    }


}