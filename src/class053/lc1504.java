package class053;

import java.util.Arrays;

public class lc1504 {
    class Solution {
        public static int MAXN = 151;
        public static int[] stack = new int[MAXN];
        public static int[] h = new int[MAXN];
        public static int r, n, cur;
        public int numSubmat(int[][] mat) {
            n = mat[0].length;
            int ans = 0;
            Arrays.fill(h, 0); // 一开始忘了清空！！！
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < n; j++) {
                    h[j] = mat[i][j] == 1 ? h[j] + 1 : 0;
                }
                ans += f(h);
            }
            return ans;
        }

        public static int f(int[] h) {
            r = 0;
            int ans = 0;
            int num1, num2;
            for (int i = 0; i < n; i++) {
                while (r > 0 && h[stack[r - 1]] >= h[i]) {
                    cur = stack[--r];
                    if (h[cur] > h[i]) { // 把cur = s[--r]一上去后，这里忘了把h[s[r - 1]]改成h[cur]
                        // cur = stack[--r]; // 一开始写到if()里了 会死循环
                        num1 = h[cur] - Math.max(h[i], r > 0 ? h[stack[r - 1]] : 0);
                        num2 = i - (r > 0 ? stack[r - 1] : -1) - 1;
                        // ans += num1 * num2; // 一开始直接乘num2了
                        ans += num1 * (num2 * (num2 + 1) / 2);
                    }
                    // 注意和之前的题目对比：相等情况的处理
                    // 之前算最大矩形的题目，同样大小的值，因为是取max，后面的ans更大会覆盖前面的ans，所以都弹出并计算就行
                    // 这里要求sum，就不能那样写，而是用if()筛选
                }
                stack[r++] = i;
            }
            while (r > 0) {
                cur = stack[--r];
                num1 = h[cur] - (r > 0 ? h[stack[r - 1]] : 0);
                num2 = n - (r > 0 ? stack[r - 1] : -1) - 1;
                // ans += num1 * num2;
                ans += num1 * (num2 * (num2 + 1) / 2);
            }
            return ans;
        }
    }
}
