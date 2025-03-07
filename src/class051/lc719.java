package class051;

import java.util.Arrays;

public class lc719 {
    class Solution {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int ans = 0;
            for (int l = 0, r = nums[nums.length - 1] - nums[0], m; l <= r;) {
                m = (l + r) >> 1;
                if (f(nums, m, k)) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return ans;
        }

        public static boolean f(int[] nums, int m, int k) {
            int ans = 0;
            for(int l = 0, r = 0; l < nums.length; l++) {
                while (r < nums.length && (nums[r] - nums[l] <= m)) {
                    r++;
                }
                ans += r - l - 1;
            }
            return ans >= k; // <= --> >=
        }
    }
}
