package class080;

public class lc473 { // 火柴拼正方形
    class Solution {
        public boolean makesquare(int[] matchsticks) {
            int sum = 0;
            int n = matchsticks.length;
            for (int i = 0; i < n; i++) {
                sum += matchsticks[i];
            }
            if (sum % 4 != 0) {
                return false;
            }
            int[] dp = new int[1 << n];
            return f(matchsticks, sum / 4, (1 << n) - 1, 0, 4, dp); // 忘了需要参数 limit
        }

        public static boolean f(int[] nums, int limit, int status, int cur, int rest, int[] dp) {
            if (rest == 0) {
                return true;
            }
            if (dp[status] != 0) {
                return dp[status] == 1;
            }
            boolean ans = false;
            for (int i = 0; i < nums.length; i++) {
                if ((status & (1 << i)) != 0 && cur + nums[i] <= limit) {
                    if (f(nums, limit, status ^ (1 << i), (cur + nums[i]) % limit, rest - (cur + nums[i] == limit ? 1 : 0), dp)) {
                        ans = true;
                        break;
                    }
                }
            }
            dp[status] = ans ? 1 : -1;
            return ans;
        }
    }
}
