package class076;

public class lc08_14 { // 布尔运算
    class Solution {
        public int countEval(String str, int result) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[][][] dp = new int[n][n][];
            int[] ans = f(s, 0, n - 1, dp);
            return ans[result];
        }

        public static int[] f(char[] s, int l, int r, int[][][] dp) {
            if (dp[l][r] != null) {
                return dp[l][r];
            }
            int f = 0;
            int t = 0;
            if (l == r) {
                f = s[l] == '0' ? 1 : 0;
                t = s[l] == '1' ? 1 : 0;
            } else {
                int[] tmp;
                for (int k = l + 1, a, b, c, d; k < r; k += 2) {
                    tmp = f(s, l, k - 1, dp);
                    a = tmp[0];
                    b = tmp[1];
                    tmp = f(s, k + 1, r, dp);
                    c = tmp[0];
                    d = tmp[1];
                    if (s[k] == '&') {
                        f += a * c + a * d + b * c;
                        t += b * d;
                    } else if (s[k] == '|') {
                        f += a * c;
                        t += b * d + a * d + b * c;
                    } else {
                        f += a * c + b * d;
                        t += a * d + b * c;
                    }
                }
            }
            dp[l][r] = new int[] {f, t};
            return dp[l][r];
        }
    }
}
