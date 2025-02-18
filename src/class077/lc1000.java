package class077;

public class lc1000 {
    class Solution {
        public int mergeStones(int[] stones, int k) {
            int n = stones.length;
            if ((n - 1) % (k - 1) != 0) { // 一开始写成 if (n % (k - 1) != 1) 不对！
                return -1;
            }
            int[][] dp = new int[n][n];
            int[] presum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                presum[i] = presum[i - 1] + stones[i - 1];
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    int ans = Integer.MAX_VALUE;
                    for (int m = i; m < j; m += k - 1) { // 因为下面调dp[m + 1][j] 所以是m < j
                        ans = Math.min(ans, dp[i][m] + dp[m + 1][j]);
                    }
                    if ((j - i) % (k - 1) == 0) { // 这里的条件和上面一样 相应的改
                        ans += presum[j + 1] - presum[i];
                    }
                    dp[i][j] = ans;
                }
            }
            return dp[0][n - 1];
        }
    }
}
