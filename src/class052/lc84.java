package class052;

public class lc84 {
    class Solution {
        public static int MAXN = 100001;
        public static int[] stack = new int[MAXN];
        public static int[][] arr = new int[MAXN][2];
        public static int r, cur, n;
        public int largestRectangleArea(int[] heights) {
            n = heights.length;
            r = 0;
            for (int i = 0; i < n; i++) {
                while (r > 0 && heights[stack[r - 1]] >= heights[i]) {
                    cur = stack[--r];
                    arr[cur][0] = r > 0 ? stack[r - 1] : -1;
                    arr[cur][1] = i;
                }
                stack[r++] = i;
            }
            while (r > 0) {
                cur = stack[--r];
                arr[cur][0] = r > 0 ? stack[r - 1] : -1;
                arr[cur][1] = n;
            }
            for (int i = n - 2; i >= 0; i--) {
                if (arr[i][1] != n && heights[arr[i][1]] == heights[i]) {
                    arr[i][1] = arr[arr[i][1]][1];
                }
            }
            int max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, heights[i] * (arr[i][1] - arr[i][0] - 1));
            }
            return max;
        }
    }

    class Solution1 { // 更快 最优
        public static int MAXN = 100001;
        public static int[] stack = new int[MAXN];
        public static int r, cur, n;
        public int largestRectangleArea(int[] heights) {
            n = heights.length;
            r = 0;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                while (r > 0 && heights[stack[r - 1]] >= heights[i]) {
                    cur = stack[--r];
                    ans = Math.max(ans, heights[cur] * (i - (r > 0 ? stack[r - 1] : -1) - 1)); // 相等的情况 后面的会修正对
                }
                stack[r++] = i;
            }
            while (r > 0) {
                cur = stack[--r];
                ans = Math.max(ans, heights[cur] * (n - (r > 0 ? stack[r - 1] : -1) - 1));
            }
            return ans;
        }
    }
}
