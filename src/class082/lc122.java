package class082;

public class lc122 { // stockⅡ
    class Solution {
        public int maxProfit(int[] prices) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    ans += prices[i] - prices[i - 1];
                }
                // 不写if 以下这一句也行
                // ans += Math.max(prices[i] - prices[i - 1], 0);
            }
            return ans;
        }
    }
}
