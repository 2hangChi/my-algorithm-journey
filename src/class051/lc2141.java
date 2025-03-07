package class051;

public class lc2141 {
    class Solution { // 很多int改long
        public long maxRunTime(int n, int[] batteries) {
            long sum = 0;
            for(int b : batteries) {
                sum += b;
            }
            long ans = 0;
            for (long l = 0, r = sum, m; l <= r;) {
                m = (l + r) >> 1;
                if (f(batteries, m, n)) {
                    ans = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return ans;
        }

        public static boolean f(int[] batteries, long m, int n) {
            long sum = 0;
            for (int b : batteries) {
                if (b >= m) {
                    n--;
                } else {
                    sum += b;
                }
            }
            return sum >= (long) n * m;
        }
    }

    class Solution1 { // ＋贪心
        public long maxRunTime(int n, int[] batteries) {
            long sum = 0;
            int max = 0;
            for (int b : batteries) {
                max = Math.max(max, b);
                sum += b;
            }
            if (sum >= (long) n * max) {
                // return sum / ((long) n * max); // 一开始写错了
                return sum / n;
            }
            int ans = 0;
            for (int l = 0, r = max, m; l <= r;) {
                m = (l + r) >> 1;
                if (f(batteries, m, n)) {
                    ans = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return ans;
        }

        public static boolean f(int[] batteries, int m, int n) {
            long sum = 0;
            for (int b : batteries) {
                if (b >= m) {
                    n--;
                } else {
                    sum += b; // 可以在这里验证提前返回
                }
            }
            return sum >= (long) n * m;
        }
    }
}
