package class076;

import java.util.Arrays;

public class lc1547 { // 记忆化搜索
    class Solution {
        public int minCost(int n, int[] cuts) {
            int len = cuts.length + 2;
            Arrays.sort(cuts);
            int[] arr = new int[len];
            for (int i = 1; i < len - 1; i++) {
                arr[i] = cuts[i - 1];
            }
            arr[0] = 0;
            arr[len - 1] = n;
            int[][] dp = new int[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    dp[i][j] = -1;
                }
            }
            return f(arr, 1, len - 2, dp);
        }

        public static int f(int[] arr, int l, int r, int[][] dp) {
            if (l > r) {
                return 0;
            }
            if (l == r) {
                return arr[r + 1] - arr[l - 1];
            }
            if (dp[l][r] != -1) {
                return dp[l][r];
            }
            int ans = Integer.MAX_VALUE;
            for (int m = l; m <= r; m++) {
                ans = Math.min(ans, f(arr, l, m - 1, dp) + f(arr, m + 1, r, dp)); //
            }
            ans += arr[r + 1] - arr[l - 1];
            dp[l][r] = ans;
            return ans;
        }
    }

    class Solution1 { // 位置依赖
        public int minCost(int n, int[] cuts) {
            int len = cuts.length + 2;
            Arrays.sort(cuts);
            int[] arr = new int[len];
            for (int i = 1; i < len - 1; i++) {
                arr[i] = cuts[i - 1];
            }
            arr[0] = 0;
            arr[len - 1] = n;
            int[][] dp = new int[len][len];
            for (int i = 1; i <= len - 2; i++) {
                dp[i][i] = arr[i + 1] - arr[i - 1];
            }
            int ans;
            for (int i = len - 3; i >= 1; i--) {
                for (int j = i + 1; j <= len - 2; j++) {
                    ans = Integer.MAX_VALUE;
                    for (int m = i; m <= j; m++) {
                        ans = Math.min(ans, dp[i][m - 1] + dp[m + 1][j]);
                    }
                    dp[i][j] = ans + arr[j + 1] - arr[i - 1];
                }
            }
            return dp[1][len - 2];
        }
    }
}
