package class082;

public class lc188 { // stockⅣ
    class Solution {
        public int maxProfit(int k, int[] prices) { // 很慢
            int n = prices.length;
            if (k >= n / 2) {
                return free(prices);
            }
            int[][] dp = new int[k + 1][n];
            for (int i = 1; i <= k; i++) { // i次交易
                for (int j = 1; j < n; j++) { // 0 - j 天范围 注意j 从1开始
                    dp[i][j] = dp[i][j - 1];
                    for (int p = 0; p < j; p++) {
                        dp[i][j] = Math.max(dp[i][j], prices[j] - prices[p] + dp[i - 1][p]);
                    }
                }
            }
            return dp[k][n - 1];
        }

        public int maxProfit1(int k, int[] prices) { // 优化枚举
            int n = prices.length;
            if (k >= n / 2) {
                return free(prices);
            }
            int[][] dp = new int[k + 1][n];
            for (int i = 1, best; i <= k; i++) {
                best = dp[i - 1][0] - prices[0];
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.max(dp[i][j - 1], prices[j] + best);
                    best = Math.max(best, dp[i - 1][j] - prices[j]);
                }
            }
            return dp[k][n - 1];
        }

        public int maxProfit2(int k, int[] prices) { // 空间压缩
            int n = prices.length;
            if (k >= n / 2) {
                return free(prices);
            }
            int[] dp = new int[n];
            for (int i = 1, best; i <= k; i++) {
                best = dp[0] - prices[0];
                for (int j = 1, up; j < n; j++) {
                    up = dp[j];
                    dp[j] = Math.max(dp[j - 1], prices[j] + best);
                    best = Math.max(best, up - prices[j]);
                }
            }
            return dp[n - 1];
        }

        public static int free(int[] prices) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                ans += Math.max(0, prices[i] - prices[i - 1]);
            }
            return ans;
        }
    }
}
