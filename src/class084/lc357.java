package class084;

public class lc357 {
    class Solution {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 1;
            }
            int ans = 10;
            for (int i = 1, s = 9, k = 9; i < n; i++, k--) {
                s *= k;
                ans += s;
            }
            return ans;
        }
    }
}
