package class081;

import java.util.Arrays;

public class lc1655 {
    class Solution {
        public boolean canDistribute(int[] nums, int[] quantity) {
            Arrays.sort(nums);
            int n = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] != nums[i]) {
                    n++;
                }
            }
            int[] cnt = new int[n];
            n = 0;
            int c = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] != nums[i]) {
                    cnt[n++] = c;
                    c = 1;
                } else {
                    c++;
                }
            }
            cnt[n++] = c; //
            int m = quantity.length;
            int[] sum = new int[1 << m];
            for (int i = 0; i < m; i++) {
                sum[1 << i] = quantity[i];
                for (int j = 1; j < (1 << i); j++) { // j < i -> j < (1 << i)
                    sum[(1 << i) | j] = sum[j] + quantity[i]; // sum[i ! j] -> sum[(1 << i) | j]l
                }
            }
            // 这样写更清晰
//            for (int i = 0, v, h; i < quantity.length; i++) {
//                v = quantity[i];
//                h = 1 << i;
//                for (int j = 0; j < h; j++) {
//                    sum[h | j] = sum[j] + v;
//                }
//            }
            int[][] dp = new int[1 << m][n];
            return f(cnt, sum, (1 << m) - 1, 0, dp);
        }

        public static boolean f(int[] cnt, int[] sum, int status, int index, int[][] dp) {
            if (status == 0) {
                return true;
            }
            if (index == cnt.length) {
                return false;
            }
            if (dp[status][index] != 0) {
                return dp[status][index] == 1;
            }
            boolean ans = false;
            for (int j = status; j != 0; j = (j - 1) & status) { // 写成 % 了 居然也通过了
                if (sum[j] <= cnt[index] && f(cnt, sum, status ^ j, index + 1, dp)) {
                    ans = true;
                    break;
                }
            }
            if (!ans) {
                ans = f(cnt, sum, status, index + 1, dp);
            }
            dp[status][index] = ans ? 1 : -1;
            return ans;
        }
    }
}
