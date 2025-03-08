package class053;

public class lc962 {
    class Solution {
        public static int MAXN = 50001;
        public static int[] stack = new int[MAXN];
        public static int r;
        public int maxWidthRamp(int[] nums) {
            int n = nums.length;
            r = 0;
            for (int i = 0; i < n; i++) {
                // 也可以先让0位置进栈 初始r=1 就不需要下面if()里面多判断r=0
                if (r == 0 || nums[stack[r - 1]] > nums[i]) { // 严格小压大进栈，不弹出
                    stack[r++] = i;
                }
            }
            int ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                while (r > 0 && nums[stack[r - 1]] <= nums[i]) {
                    ans = Math.max(ans, i - stack[--r]); // r - 1 --> --r
                }
            }
            return ans;
        }
    }
}
