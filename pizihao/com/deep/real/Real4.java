package deep.real;

/**
 * <h2>1186. 删除一次得到子数组最大和</h2>
 * 给你一个整数数组，返回它的某个非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。
 * 换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），
 * （删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 * 注意，删除一个元素后，子数组 不能为空。
 *
 * @author Create by liuwenhao on 2022/5/2 17:40
 */
public class Real4 {
    public static void main(String[] args) {

    }

    public int maximumSum(int[] arr) {
        // 没有删除的情况，初始值就是第一个元素
        int max = arr[0];
        // 删除的情况，初始值就是0，即第一个已经被删除
        int delMax = 0;
        // 返回值。数组不能为空，如果只选一个，那么就一定不会被删除
        int result = max;
        for (int i = 1; i < arr.length; i++) {
            /*
             * 存在删除的情况
             * j>0时，直接叠加值即可
             * j<0时,则选取没有删除时的值和已删除时+arr[i]的最大值
             * 为了避免max的修改带来的影响，先计算delMax
             */
            delMax = arr[i] >= 0 ? delMax + arr[i] : Math.max(max, delMax + arr[i]);
            // 没有删除就选取 max和0的最大值，如果arr[i-1]是负数的话是不需要的
            max = Math.max(0, max) + arr[i];
            // 记录最大值
            result = Math.max(Math.max(result, delMax), max);
        }
        return result;
    }
}