package class046;

import java.util.HashMap;

public class lc1124 {
    class Solution {
        public int longestWPI(int[] hours) {
            int ans = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0, pre = 0; i < hours.length; i++) {
                pre += hours[i] > 8 ? 1 : -1;
                if (pre > 0) {
                    // ans = Math.max(ans, i + 1);
                    ans = i + 1; // i + 1 一定是最大的，不用比
                } else {
                    if (map.containsKey(pre - 1)) {
                        ans = Math.max(ans, i - map.get(pre - 1)); // 注意这个pre - 1的分析，很妙
                    }
                }
                if (!map.containsKey(pre)) {
                    map.put(pre, i);
                }
            }
            return ans;
        }
    }

    class Solution1 { // 自己的优化
        public int longestWPI(int[] hours) {
            HashMap<Integer, Integer> map = new HashMap<>();
            // map.put(0, -1);
            // 可以不put(0, -1) 因为用不到，只有sum<=0时才会查sum-1，查的范围<= -1，key=0实际用不上
            int sum = 0, ans = 0;
            for (int i = 0; i < hours.length; i++) {
                sum += hours[i] > 8 ? 1 : -1;
                if (sum > 0) {
                    ans = i + 1; // + 1
                } else {
                    if (map.containsKey(sum - 1)) {
                        ans = Math.max(ans, i - map.get(sum - 1)); //
                    }
                    if (!map.containsKey(sum)) { // sum<0才有必要放进map 在这个else里sum<=0 比放在外面少执行一些
                        map.put(sum, i);
                    }
                }
            }
            return ans;
        }
    }
}
