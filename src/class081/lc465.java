package class081;

import java.util.Arrays;

public class lc465 {
    class Solution {
        public static int MAXN = 13;

        public int minTransfers(int[][] transactions) {
            int[] debt = debts(transactions);
            int n = debt.length;
            int[] dp = new int[1 << n];
            Arrays.fill(dp, -1);
            return n - f(debt, (1 << n) - 1, 0, n, dp);
        }

        public static int f(int[] debt, int s, int sum, int n, int[] dp) {
            if (dp[s] != -1) {
                return dp[s];
            }
            int ans = 0;
            if ((s & (s - 1)) != 0) { // s的位信息不只1个1
                if (sum == 0) {
                    for (int i = 0; i < n; i++) {
                        if ((s & (1 << i)) != 0) { // 写成1 << n
                            ans = f(debt, s ^ (1 << i), sum - debt[i], n, dp) + 1; // 写成1 << n
                            break; // 忘了break
                        }
                    }
                } else {
                    for (int i = 0; i < n; i++) {
                        if ((s & (1 << i)) != 0) { // 写成1 << n
                            ans = Math.max(ans, f(debt, s ^ (1 << i), sum - debt[i], n, dp)); // 写成1 << n
                        }
                    }
                }
            }
            dp[s] = ans;
            return ans;
        }

        public static int[] debts(int[][] transactions) {
            int[] help = new int[MAXN];
            for (int[] tran : transactions) {
                help[tran[0]] += tran[2];
                help[tran[1]] -= tran[2];
            }
            int n = 0;
            for (int h : help) {
                if (h != 0) {
                    n++;
                }
            }
            int[] debt = new int[n];
            n = 0;
            for (int h : help) {
                if (h != 0) {
                    debt[n++] = h;
                }
            }
            return debt;
        }
    }
}
