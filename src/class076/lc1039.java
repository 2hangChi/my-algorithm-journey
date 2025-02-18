package class076;

public class lc1039 { // 多边三角形剖分的最低得分
    class Solution { // 记忆化搜索
        public int minScoreTriangulation(int[] values) {
            int n = values.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = -1;
                }
            }
            return f(values, 0, n - 1, dp);
        }

        public static int f(int[] nums, int l, int r, int[][] dp) {
            if (dp[l][r] != -1) {
                return dp[l][r];
            }
            int ans = Integer.MAX_VALUE;
            if (l == r || l + 1 == r) {
                ans = 0;
            } else {
                for (int m = l + 1; m < r; m++) {
                    ans = Math.min(ans, f(nums, l, m, dp) + f(nums, m, r, dp) + nums[l] * nums[r] * nums[m]);
                }
            }
            dp[l][r] = ans;
            return ans;
        }
    }

    class Solution1 { // 位置依赖
        public int minScoreTriangulation(int[] values) {
            int n = values.length;
            int[][] dp = new int[n][n];
            for (int i = n - 3; i >= 0; i--) {
                for (int j = i + 2; j < n; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int m = i + 1; m < j; m++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m][j] + values[i] * values[j] * values[m]);
                    }
                }
            }
            return dp[0][n - 1];
        }
    }
}
