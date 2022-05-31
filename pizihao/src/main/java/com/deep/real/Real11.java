package com.deep.real;

/**
 * <h2>1139. 最大的以 1 为边界的正方形</h2>
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 *
 * @author Create by liuwenhao on 2022/5/6 15:14
 */
public class Real11 {
    public static void main(String[] args) {

    }

    public int largest1BorderedSquare(int[][] grid) {
        int x = grid.length;
        int y = grid[0].length;

        int[][][] dp = new int[x + 1][y + 1][2];
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    dp[i][j][1] = dp[i - 1][j][1] + 1;
                    dp[i][j][0] = dp[i][j - 1][0] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                int preMax = Math.min(dp[i][j][0], dp[i][j][1]);
                if (preMax <= max) {
                    continue;
                }
                while (preMax > max) {
                    if (dp[i - preMax + 1][j][0] >= preMax && dp[i][j - preMax + 1][1] >= preMax) {
                        max = preMax;
                        break;
                    }
                    preMax--;
                }
            }
        }
        return max * max;
    }
}