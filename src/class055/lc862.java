package class055;

public class lc862 {
    class Solution {
        public static int MAXN = 100001;
        public static long [] sum = new long[MAXN]; // 用int会有测试用例溢出
        public static int[] deque = new int[MAXN];
        public static int h, t;
        public int shortestSubarray(int[] nums, int k) {
            h = t = 0;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < n + 1; i++) {
                while (h < t && sum[deque[t - 1]] >= sum[i]) {
                    t--;
                }
                while (h < t && sum[i] - sum[deque[h]] >= k) { // sum[h] -> sum[deque[h]]
                    ans = Math.min(ans, i - deque[h++]);
                }
                deque[t++] = i;
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
    }
}
