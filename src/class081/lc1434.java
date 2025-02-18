package class081;

import java.util.Arrays;
import java.util.List;

public class lc1434 {
    class Solution {
        public static int MOD = 1000000007;

        public int numberWays(List<List<Integer>> arr) {
            int m = 0;
            for (List<Integer> hat : arr) {
                for (int h : hat) {
                    m = Math.max(m, h);
                }
            }
            int n = arr.size();
            int[] hats = new int[m + 1];
            for (int i = 0; i < n; i++) {
                for (int j : arr.get(i)) {
                    hats[j] |= 1 << i;
                }
            }
            int[][] dp = new int[m + 1][1 << n];
            for (int i = 0; i <= m; i++) {
                Arrays.fill(dp[i], -1);
            }
            return f1(hats, m, n, 1, 0, dp); // (1 <<n) - 1 -> 0
        }

        public static int f1(int[] hats, int m, int n, int hat, int s, int[][] dp) {
            if (s == (1 << n) - 1) {
                return 1;
            }
            if (hat == m + 1) {
                return 0;
            }
            if (dp[hat][s] != -1) {
                return dp[hat][s];
            }
            int ans = f1(hats, m, n, hat + 1, s, dp) % MOD;
            for (int p = 0; p < n; p++) {
                if ((hats[hat] & (1 << p)) != 0 && (s & (1 << p)) == 0) {
                    ans = (ans + f1(hats, m, n, hat + 1, s | (1 << p), dp)) % MOD;
                }
            }
            dp[hat][s] = ans;
            return ans;
        }

        public static int f2(int[] hats, int m, int n, int hat, int s, int[][] dp) { // 优化枚举
            if (s == (1 << n) - 1) {
                return 1;
            }
            if (hat == m + 1) {
                return 0;
            }
            if (dp[hat][s] != -1) {
                return dp[hat][s];
            }
            int ans = f2(hats, m, n, hat + 1, s, dp); // % MOD;
            // for (int p = 0; p < n; p++) {
            //     if ((hats[hat] & (1 << p)) != 0 && (s & (1 << p)) == 0) {
            //         ans = (ans + f1(hats, m, n, hat + 1, s | (1 << p), dp)) % MOD;
            //     }
            // }
            int cur = hats[hat];
            int rightone;
            while (cur != 0) {
                rightone = cur & -cur;
                if ((s & rightone) == 0) {
                    ans = (ans + f2(hats, m, n, hat + 1, s | rightone, dp)) % MOD;
                }
                cur ^= rightone;
            }
            dp[hat][s] = ans;
            return ans;
        }
    }
}
