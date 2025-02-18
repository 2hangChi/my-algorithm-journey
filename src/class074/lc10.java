package class074;

public class lc10 { // 正则表达式匹配
    class Solution {
        public boolean isMatch(String str, String par) {
            char[] s = str.toCharArray();
            char[] p = par.toCharArray();
            return f1(s, p, 0, 0);
        }

        public static boolean f1(char[] s, char[] p, int i, int j) {
            if (i == s.length) {
                if (j == p.length) {
                    return true;
                } else {
                    // return j + 1 < p.length && p[j + 1] == '*' && f1(s, p, i, j + 2); // 直接这一句就行 不用再if()
                    if (j + 1 < p.length) {
                        return p[j + 1] == '*' && f1(s, p, i, j + 2);
                    }
                    return false;
                }
            } else {
                if (j == p.length) {
                    return false;
                }
                if (j + 1 == p.length || p[j + 1] != '*') {
                    return (s[i] == p[j] || p[j] == '.') && f1(s, p, i + 1, j + 1);
                } else {
                    // return f1(s, p, i, j + 2) || f1(s, p, i + 1, j);
                    return f1(s, p, i, j + 2) || ((s[i] == p[j] || p[j] == '.') && f1(s, p, i + 1, j));
                }
            }
        }
    }

    class Solution1 { // 记忆化搜索
        public boolean isMatch(String str, String par) {
            char[] s = str.toCharArray();
            char[] p = par.toCharArray();
            int n = s.length;
            int m = p.length;
            int[][] dp = new int[n + 1][m + 1];
            return f1(s, p, 0, 0, dp);
        }

        public static boolean f1(char[] s, char[] p, int i, int j, int[][] dp) {
            if (dp[i][j] != 0) {
                return dp[i][j] == 1 ? true : false;
            }
            boolean ans = false;
            if (i == s.length) {
                if (j == p.length) {
                    ans = true;
                } else {
                    ans = j + 1 < p.length && p[j + 1] == '*' && f1(s, p, i, j + 2, dp);
                }
            } else {
                if (j == p.length) {
                    ans = false;
                }
                else {
                    if (j + 1 == p.length || p[j + 1] != '*') {
                        ans = (s[i] == p[j] || p[j] == '.') && f1(s, p, i + 1, j + 1, dp);
                    } else {
                        // return f1(s, p, i, j + 2) || f1(s, p, i + 1, j);
                        ans = f1(s, p, i, j + 2, dp) || ((s[i] == p[j] || p[j] == '.') && f1(s, p, i + 1, j, dp));
                    }
                }
            }
            dp[i][j] = ans ? 1 : -1;
            return ans;
        }
    }

    class Solution2 { // 位置依赖
        public boolean isMatch(String str, String par) {
            char[] s = str.toCharArray();
            char[] p = par.toCharArray();
            int n = s.length;
            int m = p.length;
            boolean[][] dp = new boolean[n + 1][m + 1];
            // i = n 最后一行
            dp[n][m] = true;
            for (int j = m - 2; j >= 0; j--) {
                dp[n][j] = p[j + 1] == '*' && dp[n][j + 2];
            }
            // i < n j = m时全false
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    if (j + 1 == p.length || p[j + 1] != '*') {
                        dp[i][j] = (s[i] == p[j] || p[j] == '.') && dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = dp[i][j + 2] || (s[i] == p[j] || p[j] == '.') && dp[i + 1][j];
                    }
                }
            }
            return dp[0][0];
        }
    }
}
