package class080;

public class lc464 { // 我能赢吗
    class Solution {
        public boolean canIWin(int n, int m) {
            if (m == 0) {
                return true;
            }
            if (n * (n + 1) / 2 < m) {
                return false;
            }
            int[] dp = new int[1 << (n + 1)];
            return f(n, (1 << (n + 1)) - 1, m, dp); // 1 << (n + 1) 要加括号再 -1
        }

        public static boolean f(int n, int status, int rest, int[] dp) {
            if (rest <= 0) {
                return false;
            }
            if (dp[status] != 0) {
                return dp[status] == 1;
            }
            boolean ans = false;
            for (int i = 1; i <= n; i++) {
                if ((status & (1 << i)) != 0 && !f(n, status ^ (1 << i), rest - i, dp)) { // status & (1 << i)要加括号
                    ans = true;
                    break;
                }
            }
            dp[status] = ans ? 1 : -1;
            return ans;
        }
    }
}
