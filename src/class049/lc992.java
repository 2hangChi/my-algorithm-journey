package class049;

import java.util.Arrays;

public class lc992 {
    class Solution {
        public int subarraysWithKDistinct(int[] nums, int k) {
            return f(nums, k) - f(nums, k - 1);
        }

        public static int MAXN = 20001;
        public static int[] cnts = new int[MAXN];

        public static int f(int[] nums, int k) {
            int ans = 0;
            Arrays.fill(cnts, 0, nums.length + 1, 0); // length -> length + 1 注意数字范围
            // 数字从1开始 长度n的数组 数字可能是1~n 要用下标1~n 需要fill(cnts, 1, n + 1, 0)
            for (int l = 0, r = 0; r < nums.length; r++) {
                if (cnts[nums[r]]++ == 0) {
                    k--;
                }
                while (k < 0) {
                    if (--cnts[nums[l++]] == 0) {
                        k++;
                    }
                }
                ans += r - l + 1; // 想清楚这一行的位置应该放在哪 一开始放到while上面去了
            }
            return ans;
        }
    }
}
