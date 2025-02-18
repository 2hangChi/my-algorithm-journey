package class076;

public class lc1312 {
    class Solution { // 暴力尝试
        public int minInsertions(String str) {
            char[] s = str.toCharArray();
            return f1(s, 0, s.length - 1);
        }

        public static int f1(char[] s, int l, int r) {
            if (l == r) {
                return 0;
            }
            if (l + 1 == r) {
                return s[l] == s[r] ? 0 : 1;
            }
            if (s[r] == s[l]) {
                return f1(s, l + 1, r - 1);
            }
            return Math.min(f1(s, l + 1, r), f1(s, l, r - 1)) + 1;
        }
    }

    class Solution1 { // 记忆化搜索
        public int minInsertions(String str) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = -1;
                }
            }
            return f1(s, 0, n - 1, dp);
        }

        public static int f1(char[] s, int l, int r, int[][] dp) {
            if (l == r) {
                return 0;
            }
            if (l + 1 == r) {
                return s[l] == s[r] ? 0 : 1;
            }
            if (dp[l][r] != -1) {
                return dp[l][r];
            }
            if (s[r] == s[l]) {
                dp[l][r] = f1(s, l + 1, r - 1, dp);
                return dp[l][r];
            }
            dp[l][r] = Math.min(f1(s, l + 1, r, dp), f1(s, l, r - 1, dp)) + 1;
            return dp[l][r];
        }
    }

    class Solution2 { // 位置依赖
        public int minInsertions(String str) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n - 1; i++) {
                dp[i][i + 1] = s[i] == s[i + 1] ? 0 : 1;
            }
            for (int i = n - 3; i >= 0; i--) {
                for (int j = i + 2; j < n; j++) {
                    if (s[i] == s[j]) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                    }
                }
            }
            return dp[0][n - 1];
        }
    }

    class Solution3 { // 空间压缩
        public int minInsertions(String str) {
            char[] s = str.toCharArray();
            int n = s.length;
            // 可以加上
//            if (n < 2) {
//                return 0;
//            }
            int[] dp = new int[n];
            for (int i = n - 2, leftdown; i >= 0; i--) {
                leftdown = dp[i + 1];
                dp[i + 1] = s[i] == s[i + 1] ? 0 : 1;
                for (int j = i + 2, backup; j < n; j++) {
                    backup = dp[j];
                    if (s[i] == s[j]) {
                        dp[j] = leftdown;
                    } else {
                        dp[j] = Math.min(dp[j - 1], dp[j]) + 1;
                    }
                    leftdown = backup;
                }
            }
            return dp[n - 1];
        }
    }
}
