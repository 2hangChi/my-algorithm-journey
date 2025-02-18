package class077;

public class lc664 { // 奇怪的打印机
    class Solution {
        public int strangePrinter(String str) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = -1;
                }
            }
            return f(s, 0, n - 1, dp);
        }
        public static int f(char[] s, int l, int r, int[][] dp) {
            if (dp[l][r] != -1) {
                return dp[l][r];
            }
            int ans = Integer.MAX_VALUE;
            if (l == r) {
                ans = 1;
            } else if (l + 1 == r) {
                ans = s[l] == s[r] ? 1 : 2;
            } else if (s[l] == s[r]) {
                ans = f(s, l, r - 1, dp);
            } else {
                for (int m = l; m < r; m++) {
                    ans = Math.min(ans, f(s, l, m, dp) + f(s, m + 1, r, dp));
                }
            }
            dp[l][r] = ans;
            return ans;
        }
    }

    class Solution1 { // 位置依赖
        public int strangePrinter(String str) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n - 1; i++) {
                dp[i][i] = 1;
                dp[i][i + 1] = s[i] == s[i + 1] ? 1 : 2;
            }
            dp[n - 1][n - 1] = 1;
            for (int i = n - 3; i >= 0; i--) {
                for (int j = i + 2; j < n; j++) {
                    if (s[i] == s[j]) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        int ans = Integer.MAX_VALUE;
                        for (int m = i; m < j; m++) {
                            ans = Math.min(ans, dp[i][m] + dp[m + 1][j]);
                        }
                        dp[i][j] = ans;
                    }
                }
            }
            return dp[0][n - 1];
        }
    }
}
