package class046;

import java.util.HashMap;

public class lc1590 {
    class Solution {
        public int minSubarray(int[] nums, int p) {
            int r = 0;
            for (int i = 0; i < nums.length; i++) {
                r = (r + nums[i]) % p;
            }
            if (r == 0) {
                return 0;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int ans = Integer.MAX_VALUE;
            for (int i = 0, r2 = 0; i < nums.length; i++) {
                r2 = (r2 + nums[i]) % p;
                if (map.containsKey((r2 + p - r) % p)) {
                    ans = Math.min(ans, i - map.get((r2 + p - r) % p));
                }
                map.put(r2, i);
            }
            return ans == nums.length ? -1 : ans;
        }
    }
}
