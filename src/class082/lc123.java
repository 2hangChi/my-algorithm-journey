package class082;

public class lc123 { // stockⅢ
    class Solution { // 会超时
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[] dp1 = new int[n];
            for (int i = 1, min = prices[0]; i < n; i++) {
                min = Math.min(min, prices[i]);
                dp1[i] = Math.max(dp1[i - 1], prices[i] - min);
            }
            int[] dp2 = new int[n];
            int ans  =0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    dp2[i] = Math.max(dp2[i], dp1[j] + prices[i] - prices[j]);
                }
                ans = Math.max(ans, dp2[i]);
            }
            return ans;
        }
    }

    class Solution2 { // 引入best数组
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[] dp1 = new int[n];
            for (int i = 1, min = prices[0]; i < n; i++) {
                min = Math.min(min, prices[i]);
                dp1[i] = Math.max(dp1[i - 1], prices[i] - min);
            }

            // best
            int[] best = new int[n];
            best[0] = dp1[0] - prices[0];
            for (int i = 1; i < n; i++) {
                best[i] = Math.max(best[i - 1], dp1[i] - prices[i]);
            }

            int[] dp2 = new int[n];
            int ans  =0;
            for (int i = 1; i < n; i++) {
                // for (int j = 0; j <= i; j++) {
                //     dp2[i] = Math.max(dp2[i], dp1[j] + prices[i] - prices[j]);
                // }
                dp2[i] = prices[i] + best[i];
                ans = Math.max(ans, dp2[i]);
            }
            return ans;
        }
    }

    class Solution3 { // 合并三个for loop
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[] dp1 = new int[n];

            int[] best = new int[n];
            best[0] = dp1[0] - prices[0];

            int[] dp2 = new int[n];
            int ans  =0;

            for (int i = 1, min = prices[0]; i < n; i++) {
                min = Math.min(min, prices[i]);
                dp1[i] = Math.max(dp1[i - 1], prices[i] - min);

                best[i] = Math.max(best[i - 1], dp1[i] - prices[i]);

                dp2[i] = prices[i] + best[i];
                ans = Math.max(ans, dp2[i]);
            }

            return ans;
        }
    }

    class Solution4 { // 数组改为单变量
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int dp1 = 0;

            int best = dp1 - prices[0];

            // int dp2 = 0; // dp2也可以不用了
            int ans  =0;

            for (int i = 1, min = prices[0]; i < n; i++) {
                min = Math.min(min, prices[i]);
                dp1 = Math.max(dp1, prices[i] - min);

                best = Math.max(best, dp1 - prices[i]);

//                dp2 = prices[i] + best;
//                ans = Math.max(ans, dp2);
                ans = Math.max(ans, prices[i] + best);
            }

            return ans;
        }
    }
}
