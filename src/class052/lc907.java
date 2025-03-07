package class052;

public class lc907 {
    class Solution {
        public static int MAXN = 30001;
        public static int MOD = 1000000007;
        public static int[] stack = new int[MAXN];
        public static int[][] ans = new int[MAXN][2];
        public static int r, cur, n;
        public int sumSubarrayMins(int[] arr) {
            r = 0;
            n = arr.length;
            for (int i = 0; i < n; i++) {
                while (r > 0 && arr[stack[r - 1]] >= arr[i]) {
                    cur = stack[--r];
                    ans[cur][0] = r > 0 ? stack[r - 1] : -1;
                    ans[cur][1] = i;
                }
                stack[r++] = i;
            }
            while (r > 0) {
                cur = stack[--r];
                ans[cur][0] = r > 0 ? stack[r - 1] : -1;
                ans[cur][1] = n; // -1 -> n
            }
            // 注意课上讲的相等情况的修正
            // for (int i = n - 2; i >= 0; i--) {
            //     if (ans[i][1] != n && arr[ans[i][1]] == arr[i]) { // != -1  -->  != n
            //         ans[i][1] = ans[ans[i][1]][1];
            //     }
            // }
            long result = 0;
            for (int i = 0; i < n; i++) {
                result = (result + (long) arr[i] * (i - ans[i][0]) * (ans[i][1] - i)) % MOD; // 一开始没有防溢出 有测试用例通过不了
            }
            return (int) result;
        }
    }
}
