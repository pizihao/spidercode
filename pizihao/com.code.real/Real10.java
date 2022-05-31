/**
 * <h2>1277. 统计全为 1 的正方形子矩阵</h2>
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 * @author Create by liuwenhao on 2022/5/5 13:25
 */
public class Real10 {
    public static void main(String[] args) {
        int[][] ints = new int[][]{};
        Real10 real10 = new Real10();
        System.out.println(real10.countSquares(ints));
    }

    public int countSquares(int[][] matrix) {
        // 大的正方形是由小的正方形组成的
        int[][] dp = new int[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                    count = count + dp[i][j];
                } else if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j]);
                    count = count + dp[i][j];
                }
            }
        }
        return count;
    }
}