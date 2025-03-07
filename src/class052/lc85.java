package class052;

public class lc85 {
    class Solution {
        public static int MAXN = 201;
        public static int[] stack = new int[MAXN];
        public static int r, cur, n;
        public int maximalRectangle(char[][] matrix) {
            n = matrix[0].length;
            int[] arr = new int[n];
            int ans = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') { // 1 --> '1'
                        arr[j] += 1;
                    } else {
                        arr[j] = 0;
                    }
                }
                ans = Math.max(ans, getMax(arr));
            }
            return ans;
        }

        public static int getMax(int[] arr) {
            r = 0;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                while (r > 0 && arr[stack[r - 1]] >= arr[i]) {
                    cur = stack[--r];
                    ans = Math.max(ans, arr[cur] * (i - (r > 0 ? stack[r - 1] : -1) - 1));
                }
                stack[r++] = i;
            }
            while (r > 0) {
                cur = stack[--r];
                ans = Math.max(ans, arr[cur] * (n - (r > 0 ? stack[r - 1] : -1) - 1));
            }
            return ans;
        }
    }
}
