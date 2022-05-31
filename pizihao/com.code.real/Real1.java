import java.util.Arrays;

/**
 * <h2>908：最小差值1</h2>
 * <p>
 * 给你一个整数数组 nums，和一个整数 k 。
 * 在一个操作中，您可以选择 0 <= i < nums.length 的任何索引 i 。将 nums[i] 改为 nums[i] + x ，其中 x 是一个范围为 [-k, k] 的整数。对于每个索引 i ，最多 只能 应用 一次 此操作。
 * nums的分数是nums中最大和最小元素的差值。
 * 在对 nums 中的每个索引最多应用一次上述操作后，返回nums 的最低 分数 。
 * <p>
 * 因为是最大值 - 最小值，所以这个值最小为0
 * max - k - (min + k) = y y >= 0
 * 如初始值中 max - min <= 2k，数组中所有的值都能转变为相等的数，那么最终的结果就是0。
 * 如果 max - min > 2k，那么最小值就是 max - k - (min + k)
 *
 * @author Create by liuwenhao on 2022/4/30 16:32
 */
public class Real1 {

    public static void main(String[] args) {
        Real1 real1 = new Real1();
    }

    public int smallestRangeI(int[] nums, int k) {
        int[] array = Arrays.stream(nums).sorted().toArray();
        int min = array[0];
        int max = array[nums.length];
        return max - min <= 2 * k ? 0 : max - min - (2 * k);
    }
}