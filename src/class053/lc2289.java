package class053;

public class lc2289 {
    class Solution {
        public static int MAXN = 100001;
        public static int[][] stack = new int[MAXN][2];
        public static int r, n;
        public int totalSteps(int[] nums) {
            r = 0; //
            n = nums.length;
            // for (int i = n - 1, cnt = 0;i >= 0; i--, cnt = 0, cur = 0) { // cur = 0
            // 一开始还搞了个cur 后来发现不需要
            for (int i = n - 1, cnt = 0;i >= 0; i--, cnt = 0) {
                while (r > 0 && nums[stack[r - 1][0]] < nums[i]) {
                    cnt = Math.max(stack[--r][1], ++cnt);
                }
                stack[r][0] = i;
                stack[r++][1] = cnt;
            }
            int ans = 0;
            while (r > 0) {
                ans = Math.max(ans, stack[--r][1]);
            }
            return ans;
        }
    }

    class Solution1 {
        public static int MAXN = 100001;
        public static int[][] stack = new int[MAXN][2];
        public static int r, n;
        public int totalSteps(int[] nums) {
            r = 0; //
            n = nums.length;
            int ans = 0;
            for (int i = n - 1, cnt = 0;i >= 0; i--, cnt = 0) {
            // for (int i = n - 1, cnt;i >= 0; i--) { // 可以这样写 然后在for()里面第一行cnt=0
                while (r > 0 && nums[stack[r - 1][0]] < nums[i]) {
                    cnt = Math.max(stack[--r][1], ++cnt);
                }
                stack[r][0] = i;
                stack[r++][1] = cnt;
                ans = Math.max(ans, cnt); // 可以直接在这里取max 不需要最后再遍历栈中剩余元素
            }
            return ans;
        }
    }
}
