package class076;

public class lc312 { // 戳气球
    class Solution { // 记忆化搜索
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[] arr = new int[n + 2];
            arr[0] = 1;
            arr[n + 1] = 1;
            for (int i = 0; i < n; i++) {
                arr[i + 1] = nums[i];
            }
            int[][] dp = new int[n + 2][n + 2];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = -1;
                }
            }
            return f(arr, 1, n, dp);
        }

        public static int f(int[] arr, int l, int r, int[][] dp) {
            if (dp[l][r] != -1) {
                return dp[l][r];
            }
            if (l == r) {
                dp[l][r] =  arr[l - 1] * arr[l] * arr[r + 1];
                return dp[l][r];
            }
            int ans = Math.max(arr[l - 1] * arr[l] * arr[r + 1] + f(arr, l + 1, r, dp), arr[l - 1] * arr[r] * arr[r + 1] + f(arr, l, r - 1, dp));
            for (int m = l + 1; m < r; m++) {
                ans = Math.max(ans, arr[l - 1] * arr[m] * arr[r + 1] + f(arr, l, m - 1, dp) + f(arr, m + 1, r, dp));
            }
            dp[l][r] = ans;
            return ans;
        }
    }

    class Solution1 { // 位置依赖
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[] arr = new int[n + 2];
            arr[0] = 1;
            arr[n + 1] = 1;
            for (int i = 0; i < n; i++) {
                arr[i + 1] = nums[i];
            }
            int[][] dp = new int[n + 2][n + 2];
            for (int i = 1; i <= n; i++) {
                dp[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
            }
            int ans;
            for (int i = n - 1; i >= 1; i--) {
                for (int j = i + 1; j <= n; j++) {
                    ans = Math.max(arr[i - 1] * arr[i] * arr[j + 1] + dp[i + 1][j], arr[i - 1] * arr[j] * arr[j + 1] + dp[i][j - 1]);
                    for (int m = i + 1; m < j; m++) {
                        ans = Math.max(ans, arr[i - 1] * arr[m] * arr[j + 1] + dp[i][m - 1] + dp[m + 1][j]);
                    }
                    dp[i][j] = ans;
                }
            }
            return dp[1][n];
        }
    }
}
