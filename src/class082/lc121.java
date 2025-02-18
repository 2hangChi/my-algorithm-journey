package class082;

public class lc121 { // stockⅠ
    class Solution {
        public int maxProfit(int[] prices) {
            int ans = 0;
            for (int i = 1, min = prices[0]; i < prices.length; i++) {
                min = Math.min(min, prices[i]);
                ans = Math.max(ans, prices[i] - min);
            }
            return ans;
        }
    }
}
