package class083;

public class lc629 { // k个逆序对数组
    class Solution {
        public int kInversePairs(int n, int k) {
            int mod = 1000000007;
            // if (k == 0) {
            //     return 1;
            // }
            int[][] dp = new int[n + 1][k + 1];
            // dp[1][0] = 1;
            // dp[1][1] = 0;
            //
            for (int i = 1; i <= n; i++) {
                dp[i][0] = 1;
            }
            // dp[2][0] = 1;
            // dp[2][1] = 1;
            // for (int i = 2; i <= k; i++) {
            //     dp[1][i] = 0;
            //     dp[2][i] = 0;
            // }
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    if (i > j) {
                        dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                    } else {
                        dp[i][j] = ((dp[i][j - 1] + dp[i - 1][j]) % mod - dp[i - 1][j - i] + mod) % mod; //
                    }
                }
            }
            return dp[n][k];
        }
    }

    class Solution1 {
        public int kInversePairs(int n, int k) {
            int mod = 1000000007;
            int[][] dp = new int[n + 1][k + 1];
//            for (int i = 1; i <= n; i++) {
//                dp[i][0] = 1;
//            }
            dp[1][0] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i][0] = 1;
                for (int j = 1; j <= k; j++) {
                    if (i > j) {
                        dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                    } else {
                        dp[i][j] = ((dp[i][j - 1] + dp[i - 1][j]) % mod - dp[i - 1][j - i] + mod) % mod; //
                    }
                }
            }
            return dp[n][k];
        }
    }
}
