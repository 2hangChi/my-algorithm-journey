package class076;

public class lc486 { // 预测赢家
    class Solution {
        public boolean predictTheWinner(int[] nums) {
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            int n = nums.length;
            int first = f1(nums, 0, n - 1);
            return first >= sum - first;
        }

        public static int f1(int[] nums, int l, int r) {
            if (l == r) {
                return nums[l];
            }
            if (l + 1 == r) {
                return Math.max(nums[l], nums[r]);
            }
            int p1 = nums[l] + Math.min(f1(nums, l + 2, r), f1(nums, l + 1, r - 1));
            int p2 = nums[r] + Math.min(f1(nums, l + 1, r - 1), f1(nums, l, r - 2));
            return Math.max(p1, p2);
        }
    }

    class Solution1 { // 记忆化搜索
        public boolean predictTheWinner(int[] nums) {
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            int n = nums.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = -1;
                }
            }
            int first = f1(nums, 0, n - 1, dp);
            return first >= sum - first;
        }

        public static int f1(int[] nums, int l, int r, int[][] dp) {
            if (l == r) {
                return nums[l];
            }
            if (l + 1 == r) {
                return Math.max(nums[l], nums[r]);
            }
            if (dp[l][r] != -1) {
                return dp[l][r];
            }
            int p1 = nums[l] + Math.min(f1(nums, l + 2, r, dp), f1(nums, l + 1, r - 1, dp));
            int p2 = nums[r] + Math.min(f1(nums, l + 1, r - 1, dp), f1(nums, l, r - 2, dp));
            dp[l][r] = Math.max(p1, p2);
            return dp[l][r];
        }
    }

    class Solution2 { // 位置依赖
        public boolean predictTheWinner(int[] nums) {
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            int n = nums.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n - 1; i++) {
                dp[i][i] = nums[i];
                dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
            }
            dp[n - 1][n - 1] = nums[n - 1];
            for (int i = n - 3; i >= 0; i--) {
                for (int j = i + 2; j < n; j++) {
                    int p1 = nums[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                    int p2 = nums[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
                    dp[i][j] = Math.max(p1, p2);
                }
            }
            return dp[0][n - 1] >= sum - dp[0][n - 1];
        }
    }
}
