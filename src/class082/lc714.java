package class082;

public class lc714 { // 买卖股票的最佳时机含手续费
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            int pre = - prices[0] - fee;
            int done = 0;
            for (int i = 1; i < prices.length; i++) {
                done = Math.max(done, pre + prices[i]);
                pre = Math.max(pre, done - prices[i] - fee);
            }
            return done;
        }
    }
}
