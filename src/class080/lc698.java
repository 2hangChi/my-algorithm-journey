package class080;

public class lc698 { // 划分为k个相等的子集
    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int n = nums.length;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % k != 0) {
                return false;
            }
            int[] dp = new int[1 << n];
            return f(nums, sum / k, (1 << n) - 1, 0, k, dp);
        }

        public static boolean f(int[] nums, int limit, int status, int cur, int rest, int[] dp) {
            if (rest == 0) {
                return true;
            }
            if (dp[status] != 0) {
                return status == 1;
            }
            boolean ans = false;
            for (int i = 0; i < nums.length; i++) {
                if ((status & (1 << i)) != 0 && cur + nums[i] <= limit) {
                    if (cur + nums[i] == limit) {
                        ans = f(nums, limit, status ^ (1 << i), 0, rest - 1, dp);
                    } else {
                        ans = f(nums, limit, status ^ (1 << i), cur + nums[i], rest, dp);
                    }
                    if (ans) {
                        break;
                    }
                }
            }
            dp[status] = ans ? 1 : -1;
            return ans;
        }
    }
}
