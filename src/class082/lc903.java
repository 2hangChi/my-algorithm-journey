package class082;

public class lc903 { // DI序列的有效排列
    class Solution { // 超时
        public int numPermsDISequence(String s) {
            return f(s.toCharArray(), 0, s.length() + 1, s.length() + 1);
        }

        public static int f(char[] s, int i, int less, int n) {
            int ans = 0;
            if (i == n) {
                ans = 1;
            } else if (i == 0 || s[i - 1] == 'D') {
                for (int nextless = 0; nextless < less; nextless++) {
                    ans += f(s, i + 1, nextless, n);
                }
            } else {
                for (int nextless = less; nextless < n - i; nextless++) {
                    ans += f(s, i + 1, nextless, n);
                }
            }
            return ans;
        }
    }

    class Solution1 { // 位置依赖
        public int numPermsDISequence(String str) {
            int mod = 1000000007;
            char[] s = str.toCharArray();
            int n = s.length + 1;
            int[][] dp = new int[n + 1][n + 1];
            dp[n][0] = 1;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j <= n; j++) {
                    if (i == 0 || s[i - 1] == 'D') {
                        for (int nextless = 0; nextless < j; nextless++) {
                            dp[i][j] = (dp[i][j] + dp[i + 1][nextless]) % mod;
                        }
                    } else {
                        for (int nextless = j; nextless < n - i; nextless++) {
                            dp[i][j] = (dp[i][j] + dp[i + 1][nextless]) % mod;
                        }
                    }
                }
            }
            return dp[0][n];
        }
    }

    class Solution2 { // 枚举优化
        public int numPermsDISequence(String str) {
            int mod = 1000000007;
            char[] s = str.toCharArray();
            int n = s.length + 1;
            int[][] dp = new int[n + 1][n + 1];
            dp[n][0] = 1;
            for (int i = n - 1; i >= 0; i--) {
                if (i == 0 || s[i - 1] == 'D') {
                    for (int j = 1; j <= n; j++) { // 可以压榨成 j <= n - i
                        dp[i][j] = (dp[i + 1][j - 1] + dp[i][j - 1]) % mod;
                    }
                } else {
                    for (int j = n - i - 1; j >= 0; j--) {
                        dp[i][j] = (dp[i + 1][j] + dp[i][j + 1]) % mod;
                    }
                }
            }
            return dp[0][n];
        }
    }
}
