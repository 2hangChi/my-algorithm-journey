package class051;

public class lc875 {
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int max = 0;
            for (int p : piles) {
                max = Math.max(max, p);
            }
            int ans = 0;
            for (int l = 1, r = max, m; l <= r;) { // l = 0 --> l = 1    l < r --> l <= r
                // l = 0会有除0报错
                m = (l + r) >> 1;
                if (f(piles, h, m)) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return ans;
        }

        public static boolean f(int[] piles, int h, int n) {
            long ans = 0; // int会溢出
            for (int p : piles) {
                ans += (p + n - 1) / n;
            }
            return ans <= h;
        }
    }
}
