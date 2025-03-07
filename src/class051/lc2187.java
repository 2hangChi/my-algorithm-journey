package class051;

public class lc2187 {
    class Solution {
        public long minimumTime(int[] time, int totalTrips) {
            int min = Integer.MAX_VALUE;
            for (int t : time) {
                min = Math.min(min, t);
            }
            long ans = 0;
            for (long l = 0, r = (long) min * totalTrips, m; l <= r;) {
                m = (l + r) >> 1;
                if (f(time, m) >= totalTrips) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return ans;
        }

        public static long f(int[] time, long m) {
            long ans = 0;
            for (int t : time) {
                ans += m / t;
            }
            return ans;
        }
    }
}
