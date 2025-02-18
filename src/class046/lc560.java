package class046;

import java.util.HashMap;

public class lc560 {
    class Solution { //
        public int subarraySum(int[] nums, int k) {
            int pre = 0, ans = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1); // 开始忘了这行
            for (int i = 0; i < nums.length; i++) {
                pre += nums[i];
                ans += map.getOrDefault(pre - k, 0);
                map.put(pre, map.getOrDefault(pre, 0) + 1);
            }
            return ans;
        }
    }
}
