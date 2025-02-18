package class081;

import java.util.Arrays;

public class lc1994 {
    class Solution {
        public static int MAXV = 30;

        public static int LIMIT = (1 << 10);

        public static int MOD = 1000000007;

        public static int[] own = { 0b0000000000, // 0
                0b0000000000, // 1
                0b0000000001, // 2
                0b0000000010, // 3
                0b0000000000, // 4
                0b0000000100, // 5
                0b0000000011, // 6
                0b0000001000, // 7
                0b0000000000, // 8
                0b0000000000, // 9
                0b0000000101, // 10
                0b0000010000, // 11
                0b0000000000, // 12
                0b0000100000, // 13
                0b0000001001, // 14
                0b0000000110, // 15
                0b0000000000, // 16
                0b0001000000, // 17
                0b0000000000, // 18
                0b0010000000, // 19
                0b0000000000, // 20
                0b0000001010, // 21
                0b0000010001, // 22
                0b0100000000, // 23
                0b0000000000, // 24
                0b0000000000, // 25
                0b0000100001, // 26
                0b0000000000, // 27
                0b0000000000, // 28
                0b1000000000, // 29
                0b0000000111 // 30
        };

        public int numberOfGoodSubsets(int[] nums) {
            int[] cnt = new int[MAXV + 1];
            for (int n : nums) {
                cnt[n]++;
            }
            int[][] dp = new int[MAXV + 1][LIMIT];
            for (int i = 0; i <= MAXV; i++) {
                Arrays.fill(dp[i], -1);
            }
            int ans = 0;
            for (int s = 1; s < LIMIT; s++) {
                ans = (ans + f1(MAXV, s, cnt, dp)) % MOD;
            }
            return ans;
        }

        public static int f1(int i, int s, int[] cnt, int[][] dp) {
            if (dp[i][s] != -1) {
                return dp[i][s];
            }
            int ans = 0;
            if (i == 1) {
                if (s == 0) {
                    ans = 1;
                    for (int p = 0; p < cnt[1]; p++) {
                        ans = (ans << 1) % MOD;
                    }
                }
            } else {
                ans = f1(i - 1, s, cnt, dp);
                // for (int p = 1; p <= i; p++) {
                //     if (s != 0 && own[p] != 0 && (s & own[p]) == own[p]) {
                //         ans = (ans + f1(i - 1, s ^ own[p], cnt, dp) * cnt[p]) % MOD;
                //     }
                // }
                if (s != 0 && own[i] != 0 && (s & own[i]) == own[i]) { // 应该加一个条件: cnt[i] != 0 而s != 0 可有可无
                    // ans = (ans + f1(i - 1, s ^ own[i], cnt, dp) * cnt[i]) % MOD;
                    ans = (int) ((ans + (long) f1(i - 1, s ^ own[i], cnt, dp) * cnt[i]) % MOD);
                }
            }
            dp[i][s] = ans;
            return ans;
        }

        public static int[] cnt = new int[MAXV + 1];

        public static int[] dp = new int[LIMIT];

        public int numberOfGoodSubsets2(int[] nums) { // 位置依赖 + 空间压缩
            Arrays.fill(cnt, 0);
            Arrays.fill(dp, 0);
            for (int n : nums) {
                cnt[n]++;
            }
            dp[0] = 1;
            for (int i = 0; i < cnt[1]; i++) {
                dp[0] = (dp[0] << 1) % MOD;
            }
            for (int i = 2; i <= MAXV; i++) {
                for (int s = LIMIT - 1; s >= 1; s--) {
                    if (cnt[i] != 0 && own[i] != 0 && (s & own[i]) == own[i]) { // cnt[i] != 0 && own[i] != 0 这两个条件拆出来 提前判断
                        dp[s] = (int) ((dp[s] + (long) dp[s ^ own[i]] * cnt[i]) % MOD);
                    }
                }
            }
//            for (int i = 2; i <= MAXV; i++) {
//                if (cnt[i] != 0 && own[i] != 0) {
//                    for (int s = LIMIT - 1; s >= 1; s--) {
//                        if ((s & own[i]) == own[i]) {
//                            dp[s] = (int) ((dp[s] + (long) dp[s ^ own[i]] * cnt[i]) % MOD);
//                        }
//                    }
//                }
//            }
            int ans = 0;
            for (int i = 1; i < LIMIT; i++) {
                ans = (ans + dp[i]) % MOD;
            }
            return ans;
        }
    }
}
