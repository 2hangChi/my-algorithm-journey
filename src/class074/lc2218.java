package class074;

import java.util.List;

public class lc2218 { // 从栈中取出K个硬币的最大面值和
    class Solution {
        public int maxValueOfCoins(List<List<Integer>> piles, int m) {
            int n = piles.size(); // .length
            int[][] dp = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) { // 1...n
                List<Integer> team = piles.get(i - 1);
                int t = Math.min(team.size(), m); // team.size()
                int[] presum = new int[t + 1];
                for (int j = 1, sum = 0; j <= t; j++) {
                    sum += team.get(j - 1);
                    presum[j] = sum;
                }
                for (int j = 0; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j];
                    for (int k = 1; k <= t; k++) { // k <= Math.min(t, j) 直接写在for里
                        if (j >= k) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k] + presum[k]);
                        }
                    }
                }
            }
            return dp[n][m];
        }
    }

    class Solution1 { // 空间压缩
        public int maxValueOfCoins(List<List<Integer>> piles, int m) {
            int n = piles.size(); // .length
            int[] dp = new int[m + 1];
            for (int i = 1; i <= n; i++) { // 1...n
                List<Integer> team = piles.get(i - 1);
                int t = Math.min(team.size(), m); // team.size()
                int[] presum = new int[t + 1];
                for (int j = 1, sum = 0; j <= t; j++) {
                    sum += team.get(j - 1);
                    presum[j] = sum;
                }
                for (int j = m; j >= 0; j--) {
                    // dp[j] = dp[j];
                    for (int k = 1; k <= Math.min(t, j); k++) {
                        dp[j] = Math.max(dp[j], dp[j - k] + presum[k]);
                    }
                }
            }
            return dp[m];
        }
    }
}
