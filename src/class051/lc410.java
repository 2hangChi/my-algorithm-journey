package class051;

public class lc410 {
    class Solution {
        public int splitArray(int[] nums, int k) {
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            int ans = 0;
            for (int l = 0, r = sum, m; l <= r;) {
                m = (l + r) >> 1;
                if (f(nums, m) <= k) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return ans;
        }

        public static int f(int[] nums, int m) {
            int ans = 1; // 0 -> 1 这里应该是1 初始应该就有一块
            for (int i = 0, pre = 0; i < nums.length; i++) {
                if (nums[i] > m) {
                    return Integer.MAX_VALUE;
                }
                pre += nums[i];
                if (pre > m) {
                    pre = nums[i];  // 最后pre一定会留一部分 这一部分不会引起ans++ 所以初始ans应该为1 这样上次ans++就对应现在pre里的部分
                    ans++;
                }
            }
            return ans;
        }
    }
}
