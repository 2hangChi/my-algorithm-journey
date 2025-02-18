package class083;

import java.util.Arrays;

public class lc1235 { // 规划兼职工作
    class Solution {
        public static int MAXN = 50001;

        public static int[][] jobs = new int[MAXN][3];

        public static int[] dp = new int[MAXN];

        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = startTime.length;
            for (int i = 0; i < n; i++) {
                jobs[i][0] = startTime[i];
                jobs[i][1] = endTime[i];
                jobs[i][2] = profit[i];
            }
            Arrays.sort(jobs, 0, n, (a, b) -> a[1] - b[1]);
            // int[] dp = new int[n];
            dp[0] = jobs[0][2];
            for (int i = 1; i < n; i++) {
                dp[i] = dp[i - 1];
                // int e = find(jobs[i][0], n);
                int e = find(jobs[i][0], i);
                dp[i] = Math.max(dp[i], (e != -1 ? dp[e] : 0) + jobs[i][2]);
            }
            return dp[n - 1];
        }

        public static int find(int s, int n) {
            int find = -1;
            // for (int l = 0, r = n - 1, m; l < r;) {
            for (int l = 0, r = n, m; l <= r;) { // <= 写成 <
                m = (l + r) / 2;
                if (jobs[m][1] <= s) {
                    find = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return find;
        }
    }
}
