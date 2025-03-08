package class054;

public class lc239 {
    class Solution {
        public static int MAXN = 100001;
        public static int[] deque = new int[MAXN];
        public static int h, t;
        public int[] maxSlidingWindow(int[] nums, int k) {
            h = t = 0;
            int n = nums.length;
            for (int i = 0; i < k - 1; i++) { // k - 1长度的窗口
                while (h < t && nums[deque[t - 1]] <= nums[i]) {
                    t--;
                }
                deque[t++] = i;
            }
            int[] ans = new int[n - k + 1];
            for (int l = 0, r = k - 1; r < n; r++) {
                while (h < t && nums[deque[t - 1]] <= nums[r]) { // if -> while
                    t--;
                }
                deque[t++] = r;
                ans[l] = nums[deque[h]];
                // if (h == l++) {
                if (deque[h] == l++) {
                    h++;
                }
            }
            return ans;
        }
    }
}