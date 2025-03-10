package class055;

import java.util.Arrays;

public class lc2071 {
    class Solution {
        public static int MAXN = 50001;
        public static int[] deque = new int[MAXN];
        public static int h, t;
        public static int[] tasks;
        public static int[] workers;
        public int maxTaskAssign(int[] ts, int[] ws, int pills, int strength) {
            tasks = ts;
            workers = ws;
            Arrays.sort(tasks);
            Arrays.sort(workers);
            int n = tasks.length;
            int m = workers.length;
            int ans = 0;
            for (int l = 0, r = Math.min(n, m), mid; l <= r;) {
                mid = (l + r) >> 1;
                if (ok(m - mid, m - 1, 0, mid - 1, pills, strength)) {
                    ans = mid; // 一开始mid写成m 半天没找到错在哪 避免难区分的命名
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans;
        }

        public static boolean ok(int wl, int wr, int tl, int tr, int pills, int strength) {
            h = t = 0;
            for (int i = wl, j = tl; i <= wr; i++) {
                while (j <= tr && workers[i] >= tasks[j]) {
                    deque[t++] = j;
                    j++;
                }
                if (h < t && workers[i] >= tasks[deque[h]]) {
                    h++;
                } else {
                    while (j <= tr && workers[i] + strength >= tasks[j]) {
                        deque[t++] = j;
                        j++;
                    }
                    if (h < t) {
                        t--;
                        pills--;
                    } else {
                        return false;
                    }
                }
            }
            return pills >= 0;
        }
    }
}
