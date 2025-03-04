package class047;

public class lc1109 {
    class Solution {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] dif = new int[n + 1];
            for (int i = 0; i < bookings.length; i++) {
                dif[bookings[i][0] - 1] += bookings[i][2];
                dif[bookings[i][1]] -= bookings[i][2];
            }
            int[] ans = new int[n];
            ans[0] = dif[0];
            for (int i = 1; i < n; i++) {
                ans[i] = ans[i - 1] + dif[i];
            }
            return ans;
        }
    }
}
