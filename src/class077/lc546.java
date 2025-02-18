package class077;

public class lc546 { // 移除盒子
    class Solution {
        public int removeBoxes(int[] boxes) {
            int n = boxes.length;
            int[][][] dp = new int[n][n][n];
            return f(boxes, 0, n - 1, 0, dp);
        }

        public static int f(int[] boxes, int l, int r, int k, int[][][] dp) {
            if (l > r) {
                return 0;
            }
            if (dp[l][r][k] != 0) {
                return dp[l][r][k];
            }
            int s = l;
            while (s + 1 <= r && boxes[l] == boxes[s + 1]) {
                s++;
            }
            int cnt = k + s - l + 1;
            int ans = cnt * cnt + f(boxes, s + 1, r, 0, dp);
            // int m = s + 2; // 且这样写逻辑也是错的 这样的逻辑是碰到第一个组头就合并 递归连续调起来就是所有相同的都留一起消除 或者前面的一些相同组一起消 而不能跳过中间的组：only前面和后面的一起消
            // while (m <= r && boxes[m] != boxes[l]) {
            //     m++;
            // }
            // ans = Math.max(ans, f(boxes, s + 1, m - 1, 0, dp) + f(boxes, m, r, cnt, dp)); // 这里会超出索引范围 因为l < r 会执行dp[s +1][m - 1]

//            if (m <= r) { // 加上前提后不会过界出错 但一部分数据不通过（结果少几分） 是上面分析的原因吗？
//                ans = Math.max(ans, f(boxes, s + 1, m - 1, 0, dp) + f(boxes, m, r, cnt, dp));
//            }
            // 这样的逻辑是：碰到第一个组头 就决定合体或不合体
            // 似乎确实不能跳过一些中间组 前后的组合体

            for (int m = s + 2; m <= r; m++) { // 应该遍历每一个组头 这样可能性都照顾到了
                if (boxes[m] == boxes[l] && boxes[m] != boxes[m - 1]) {
                    ans = Math.max(ans, f(boxes, s + 1, m - 1, 0, dp) + f(boxes, m, r, cnt, dp));
                }
            }
            dp[l][r][k] = ans;
            return ans;
        }
    }
}
