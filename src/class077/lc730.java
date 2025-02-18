package class077;

import java.util.Arrays;

public class lc730 { // 统计不同回文子序列
    class Solution {
        public int countPalindromicSubsequences(String str) {
            int mod = 1000000007;
            char[] s = str.toCharArray();
            int n = s.length;
            int[] last = new int[256];
            int[] left = new int[n];
            int[] right = new int[n];
            Arrays.fill(last, -1);
            for (int i = 0; i < n; i++) {
                left[i] = last[s[i]];
                last[s[i]] = i;
            }
            Arrays.fill(last, -1);
            for (int i = n - 1; i >= 0; i--) {
                right[i] = last[s[i]];
                last[s[i]] = i;
            }
            long[][] dp = new long[n][n]; // 要用long类型
            for (int i = 0; i < n - 1; i++) {
                dp[i][i] = 1;
                dp[i][i + 1] = 2;
            }
            dp[n - 1][n - 1] = 1;
            for (int i = n - 3; i >= 0; i--) {
                for (int j = i + 2; j < n; j++) {
                    if(s[i] != s[j]) {
                        dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1] + mod) % mod;
                    } else {
                        if (right[i] == j) {
                            dp[i][j] = (dp[i + 1][j - 1] * 2 + 2) % mod;
                        } else if (right[i] == left[j]) {
                            dp[i][j] = (dp[i + 1][j - 1] * 2 + 1) % mod;
                        } else {
                            dp[i][j] = (dp[i + 1][j - 1] * 2 - dp[right[i] + 1][left[j] - 1] + mod) % mod; //
                        }
                    }
                }
            }
            return (int) dp[0][n - 1];
        }
    }
}
