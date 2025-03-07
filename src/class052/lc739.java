package class052;

public class lc739 {
    class Solution {
        public static int MAXN = 100001;
        public static int[] stack = new int[MAXN];
        public static int r;
        public int[] dailyTemperatures(int[] temperatures) {
            r = 0;
            int n = temperatures.length;
            int[] ans = new int[n];
            for (int i = 0, cur; i < n; i++) {
                while (r > 0 && temperatures[stack[r - 1]] < temperatures[i]) {
                    cur = stack[--r];
                    ans[cur] = i - cur;
                }
                stack[r++] = i;
            }
            return ans;
        }
    }
}
