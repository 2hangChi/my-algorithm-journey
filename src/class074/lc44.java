package class074;

public class lc44 { // 通配符匹配
    class Solution {
        public boolean isMatch(String str, String par) {
            char[] s = str.toCharArray();
            char[] p = par.toCharArray();
            int n = s.length;
            int m = p.length;
            boolean[][] dp = new boolean[n + 1][m + 1];
            dp[n][m] = true;
//            for (int j = m - 1; j >= 0 && p[j] == '*'; j--) {
//                dp[n][j] = true;
//            }
            // 上面这样写也行
            for (int j = m - 1; j >= 0; j--) {
                dp[n][j] = p[j] == '*' && dp[n][j + 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    if (p[j] != '*') {
                        // dp[i][j] = s[i] == p[j] || p[j] == '?';
                        dp[i][j] = (s[i] == p[j] || p[j] == '?') && dp[i + 1][j + 1]; // i和j匹配完后还要看后续结果，一开始忘了
                    } else {
                        // dp[i][j] = dp[i + 1][j];
                        dp[i][j] = dp[i + 1][j] || dp[i][j + 1]; // 对于'*' 1.匹配[i] 2.匹配空串  一开始忘了2
                    }
                }
            }
            return dp[0][0];
        }
    }
}
